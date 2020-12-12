package com.Nona9961.controllerContainer;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * <P>Controller及其mappings信息存放的地方</P>
 * 需要实现添加和遍历方法
 */
public abstract class ControllerContainer {
    protected HashMap<String, Object> controllers = new HashMap<>();
    protected HashMap<String, Method> methods = new HashMap<>();

    protected ControllerContainer() {
    }


    public abstract void addController(String controllerName, Object obj);

    public abstract void addMethods(String url, Method method);

    public abstract Object getController(String controllerName);

    public abstract Method getMethods(String url);
}