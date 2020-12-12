package com.Nona9961.controllerContainer;

import java.lang.reflect.Method;

/**
 * <P>ControllerContainer实现类</P>
 * 保证单例
 */
public class ControllerContainerImpl extends ControllerContainer {
    private static ControllerContainer controllerContainer = new ControllerContainerImpl();

    public ControllerContainerImpl() {
        super();
    }

    public static ControllerContainer getControllerContainer() {
        return controllerContainer;
    }

    @Override
    public void addController(String controllerName, Object obj) {
        controllers.put(controllerName, obj);
    }

    @Override
    public void addMethods(String url, Method method) {
        methods.put(url, method);
    }

    @Override
    public Object getController(String controllerName) {
        return controllers.get(controllerName);
    }

    @Override
    public Method getMethods(String url) {
        return methods.get(url);
    }


}
