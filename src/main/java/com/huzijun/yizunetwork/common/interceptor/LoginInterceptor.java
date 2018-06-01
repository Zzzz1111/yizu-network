package com.huzijun.yizunetwork.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    public class LoginInterceptor  implements HandlerInterceptor  {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/user");
//            System.out.println(request.getRequestURI().toString());
            return  false;
        }

//        System.out.println("preHandle");
//        return true;

//        System.out.println(request.getRequestURI().toString());
        return true;
    }
}
