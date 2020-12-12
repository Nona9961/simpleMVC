package com.Nona9961.servlet;

import com.Nona9961.adapter.SimpleMVCAdapter;
import com.Nona9961.exceptions.SimpleMVCException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * SimpleMVCServlet  -- dispatchServlet
 */
public class SimpleMVCServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleMVCAdapter simpleMVCAdapter = new SimpleMVCAdapter();
        Method method = null;
        try {
            method = simpleMVCAdapter.getMethodByMapping(req);
        } catch (SimpleMVCException e) {
            throw new ServletException(e.getMessage());
        }
        resp.getWriter().write(method.getName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
