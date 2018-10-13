package com.boot.thread.printRequest;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

public class WithHoldFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // log.info("WithHoldFilter开始执行了");
        System.out.println("WithHoldFilter开始执行了");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
