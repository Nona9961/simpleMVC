package com.Nona9961.adapter;

import com.Nona9961.exceptions.SimpleMVCException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 解析器标准
 * 根据Mapping获得对应的Method
 */
public interface AbstractAdapter {
    Method getMethodByMapping(HttpServletRequest request) throws SimpleMVCException;
}
