package com.Nona9961.init;

import com.Nona9961.servlet.SimpleMVCServlet;
import com.Nona9961.utils.Constant;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * tomcat启动的时候自动调用该实现类
 * 需要在resources/META-INF/services里面配置一下
 * 首先执行initContainer里面的init方法：读取配置文件、获取scan路径和servlet拦截url
 * 接着执行scanController方法注册Controller
 *
 */
public class SimpleMVCServletContainerInitializer implements ServletContainerInitializer {
    /**
     * @param set            该集合里面装@HandlesTypes里面声明的.class对象
     * @param servletContext Servlet上下文，可以直接向里面注册servlet
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        InitContainer container = new InitContainerImpl();
        try {
            container.initConfig();
            container.scanController();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
        ServletRegistration.Dynamic simpleMVCServlet = servletContext.addServlet(Constant.DEFAULT_SERVLET_NAME, new SimpleMVCServlet());
        System.out.println("servletUrl = " + container.servletUrl);
        simpleMVCServlet.addMapping(container.servletUrl);
    }
}
