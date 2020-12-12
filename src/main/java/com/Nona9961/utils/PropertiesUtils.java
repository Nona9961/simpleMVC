package com.Nona9961.utils;

import com.Nona9961.exceptions.SimpleMVCException;
import com.Nona9961.exceptions.SimpleMVCExceptionEnum;

import java.io.InputStream;
import java.util.Properties;

/**
 * Properties工具类
 */
public class PropertiesUtils {
    private static String path;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        PropertiesUtils.path = path;
    }

    /**
     * <p>根据keyName获取properties配置文件对应的value</p>
     *
     * @param keyName properties里的
     * @return
     */
    public static String getProperties(String keyName) throws Exception {
        if (path == null) {
            // todo log记录 SimpleMVCExceptionEnum.PROPERTIES_PATH_IS_NULL
            throw new SimpleMVCException(SimpleMVCExceptionEnum.PROPERTIES_PATH_IS_NULL);
        }
        String result = null;
        InputStream resourceAsStream= null;
        try {
            Properties properties = new Properties();
            resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
            properties.load(resourceAsStream);
            result = (String) properties.get(keyName);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("获取属性失败");
        }finally {
            resourceAsStream.close();
        }
        return result;
    }
}
