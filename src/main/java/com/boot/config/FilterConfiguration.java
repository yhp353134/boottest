package com.boot.config;

import com.boot.thread.printRequest.RequestFilter;
import com.boot.thread.printRequest.WithHoldFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 自定义filter
 * */
@Configuration
public class FilterConfiguration {

    @Bean
    public Filter getRequestFilter() {
        return new RequestFilter(); //日期过滤器
    }

    @Bean
    public Filter getWithHoldFilter() {
        return new WithHoldFilter(); //代扣过滤器
    }

    /**
     * 注册日志请求的过滤器执行方法，让系统知道
     * */
    @Bean
    public FilterRegistrationBean requestFilter() {
        FilterRegistrationBean requestFilter = new FilterRegistrationBean();
        requestFilter.setFilter(getRequestFilter());
        requestFilter.addUrlPatterns("/*");
        requestFilter.setOrder(1); // 过滤器执行顺序,数字越小，越先执行
        return requestFilter;
    }

    /**
     * 注册代扣的过滤器执行方法，让系统知道
     * */
    @Bean
    public FilterRegistrationBean withHoldFilter() {
        FilterRegistrationBean withHoldFilter = new FilterRegistrationBean();
        withHoldFilter.setFilter(getWithHoldFilter());
        withHoldFilter.addUrlPatterns("/withhold/*");
        withHoldFilter.setOrder(2); // 过滤器执行顺序,数字越小，越先执行
        return withHoldFilter;
    }

}
