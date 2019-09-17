package com.onnoa.aspect.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义注解类
 * @Author: onnoA
 * @Date: 2019/7/28 11:09
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LongOu {

    /**
     * 何种场景下的通用日志打印
     *
     * @return
     */
    String module() default "";

}
