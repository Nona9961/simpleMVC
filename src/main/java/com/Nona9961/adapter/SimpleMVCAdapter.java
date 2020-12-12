package com.Nona9961.adapter;

import com.Nona9961.controllerContainer.ControllerContainer;
import com.Nona9961.controllerContainer.SimpleMVCControllerContainerFactory;
import com.Nona9961.exceptions.SimpleMVCException;
import com.Nona9961.exceptions.SimpleMVCExceptionEnum;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

public class SimpleMVCAdapter implements AbstractAdapter {
    @Override
    public Method getMethodByMapping(HttpServletRequest request) throws SimpleMVCException {
        ControllerContainer controllerContainer = new SimpleMVCControllerContainerFactory().getControllerContainer();
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String url = requestURI.replaceAll(contextPath, "");
        Method method = controllerContainer.getMethods(url);
        if (method == null) {
            throw new SimpleMVCException(SimpleMVCExceptionEnum.MAPPING_NOT_FOUND);
        }
        return method;
    }
}
