package com.boot.thread.printRequest;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // log.info("RequestFilter开始执行了");
        // 自定义拦截器实现的具体方法
        System.out.println("RequestFilter开始执行了");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        RequestHolder.set(Thread.currentThread().getId(), request.getServletPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
