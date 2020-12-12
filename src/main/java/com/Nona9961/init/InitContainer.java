package com.Nona9961.init;

import com.Nona9961.exceptions.SimpleMVCException;

/**
 * <P>负责初始化加载Controller</P>
 * 需要完成
 *  1. 读取配置文件，确定要扫描的包
 *  2. 将扫描出来的Controller加入ControllerContainer里面
 */
public abstract class InitContainer {
    protected String servletUrl;
    protected String scanPath;

    public String getServletUrl() {
        return servletUrl;
    }

    public void setServletUrl(String servletUrl) {
        this.servletUrl = servletUrl;
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public abstract void  initConfig() throws SimpleMVCException;
    public abstract void  scanController() throws SimpleMVCException;
}
