package com.example.securitydemo.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/31 14:10
 */
@Component
public class CustomAccessDeinedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type","application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\"code\":\"403\",\"msg\":\"权限不足，无法访问!\"}");
        writer.flush();
        writer.close();
    }
}
