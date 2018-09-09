package com.boot.common.thread.annoations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不推荐
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) // 实际项目使用RUNTIME
public @interface NoRecommend {
    
    String value() default "";
}
