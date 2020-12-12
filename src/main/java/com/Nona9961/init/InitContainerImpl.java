package com.Nona9961.init;

import com.Nona9961.annotation.Controller;
import com.Nona9961.annotation.Mapping;
import com.Nona9961.controllerContainer.ControllerContainer;
import com.Nona9961.controllerContainer.ControllerContainerFactory;
import com.Nona9961.controllerContainer.ControllerContainerImpl;
import com.Nona9961.controllerContainer.SimpleMVCControllerContainerFactory;
import com.Nona9961.exceptions.SimpleMVCException;
import com.Nona9961.exceptions.SimpleMVCExceptionEnum;
import com.Nona9961.utils.Constant;
import com.Nona9961.utils.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * 获取配置文件的mapping和scanPath
 */
public class InitContainerImpl extends InitContainer {
    private ControllerContainer controllerContainer;

    public InitContainerImpl() {
        ControllerContainerFactory factory = new SimpleMVCControllerContainerFactory();
        controllerContainer = factory.getControllerContainer();
    }

    /**
     * 仅仅用来获取properties的mapping和scanPath
     *
     * @throws SimpleMVCException 自定义Exception
     */
    @Override
    public void initConfig() throws SimpleMVCException {
        PropertiesUtils.setPath(Constant.PROPERTIES_PATH);
        try {
            servletUrl = PropertiesUtils.getProperties("SIMPLEMVC.SERVLET.MAPPING");
            scanPath = PropertiesUtils.getProperties("SIMPLEMVC.INIT.SCANPACKAGE");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SimpleMVCException(SimpleMVCExceptionEnum.PROPERTIES_ATTR_INVALID);
        }
    }

    /**
     * 扫描包路径下的类，递归扫描所有类
     * 在register方法中进行注册
     *
     * @throws SimpleMVCException 自定义Exception
     */
    @Override
    public void scanController() throws SimpleMVCException {
        System.out.println("scanController我进来了！");
        if (null == scanPath) {
            throw new SimpleMVCException(SimpleMVCExceptionEnum.PROPERTIES_ATTR_INVALID);
        }
        Enumeration<URL> urls = null;
        try {
            urls = Thread.currentThread().getContextClassLoader().getResources(scanPath);
            System.out.println("urls.hasMoreElements() = " + urls.hasMoreElements());
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String type = url.getProtocol();
                if ("file".equals(type)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    File file = new File(filePath);
                    if (file.isDirectory()) {
                        register(scanPath, file);
                    }
                } else {
                    // 看看spring怎么做的吧……感觉有一堆东西
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new SimpleMVCException(SimpleMVCExceptionEnum.FILE_READ_ERROR);
        }
    }

    /**
     * doRegister真正的注册方法
     *
     * @param file 传进来的文件夹
     */
    private void register(String pckName, File file) throws SimpleMVCException {
        System.out.println("register我进来了！");
        File[] files = file.listFiles();
        for (File subFile : files) {
            if (subFile.isFile() && subFile.getName().endsWith(".class")) {
                doRegister(pckName, subFile);
            } else if (subFile.isDirectory()) {
                register(scanPath + "/" + subFile.getName(), subFile);
            }
        }
    }

    /**
     * 扫描传进来的.class文件，看看注解@Controller
     * mappingDomain为@Mapping在类上注解的内容
     *
     * @param file 传进来的文件
     */
    private void doRegister(String pckName, File file) throws SimpleMVCException {
        System.out.println("doRegister，我进来了！");
        StringBuilder preUrl = new StringBuilder("");
        String path = (pckName + "/" + file.getName().replaceAll(".class", "")).replace('/', '.');
        try {
            Class<?> clazz = Class.forName(path);
            if (clazz.getAnnotation(Controller.class) != null) {
                controllerContainer.addController(clazz.getName(), clazz.getConstructor().newInstance());
                Mapping mappingDomain = clazz.getAnnotation(Mapping.class);
                if (mappingDomain != null) {
                    preUrl.append(mappingDomain.value());
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Mapping mappingMethod = method.getAnnotation(Mapping.class);
                    if (mappingMethod != null) {
                        String sufUrl = mappingMethod.value().trim();
                        if ("".equals(sufUrl) && preUrl.length() == 0) {
                            throw new SimpleMVCException(SimpleMVCExceptionEnum.CONTROLLER_WITHOUT_MAPPING);
                        }
                        if (controllerContainer.getMethods(preUrl.append(sufUrl).toString()) != null) {
                            throw new SimpleMVCException(SimpleMVCExceptionEnum.MAPPING_AMBIGUOUS);
                        }
                        controllerContainer.addMethods(preUrl.toString(), method);// notice preUrl had appended sufUrl
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SimpleMVCException(SimpleMVCExceptionEnum.CONTROLLER_CREATED_FAILURE);
        }
    }
}
