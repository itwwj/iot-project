package com.github.iot.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义标记注解
 * @author jie
 */
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTopicMap {

    String topic() default "";
    int qos() default 0;
}