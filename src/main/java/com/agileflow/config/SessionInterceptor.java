package com.agileflow.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        
        // Bỏ qua các resource tĩnh và trang login
        if (requestURI.contains("/css/") || requestURI.contains("/js/") || 
            requestURI.contains("/images/") || requestURI.contains("/login")) {
            return true;
        }
        
        if (session == null || session.getAttribute("SPRING_SECURITY_CONTEXT") == null) {
            response.sendRedirect("/login");
            return false;
        }
        
        return true;
    }
}