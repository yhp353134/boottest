package com.boot.limiter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Configuration
public class WebConfigurers extends WebMvcConfigurerAdapter {

    @Autowired
    public JedisPool jedisPool;


    /**只有返回ModelAndView的地址才会进来,只对web页面的请求**/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
                if (rateLimiter != null) {
                    int limit = rateLimiter.limit();
                    int timeout = rateLimiter.timeout();
                    Jedis jedis = jedisPool.getResource();
                        /*String token = RedisRateLimiter.acquireTokenFromBucket(jedis, limit, timeout);
                        if (token == null) {
                            response.sendError(500);
                            System.out.println("返回token:" + token);
                            return false;
                        }
                        System.out.println("token ->" + token);*/
                    jedis.close();
                }
                return true;
            }
        }).addPathPatterns("/*"); // 校验规则,路径，可以配置在某个controller上面
    }

}
