package com.Nona9961.init;

import com.Nona9961.servlet.SimpleMVCServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * tomcat启动的时候自动调用该实现类
 * 需要在resources/META-INF/services里面配置一下
 */
public class SimpleMVCServletContainerInitializer implements ServletContainerInitializer {
    /**
     * @param set 该集合里面装@HandlesTypes里面声明的.class对象
     * @param servletContext    Servlet上下文，可以直接向里面注册servlet
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic simpleMVCServlet = servletContext.addServlet("SimpleMVCServlet", new SimpleMVCServlet());
        simpleMVCServlet.addMapping("/hello");
    }
}
