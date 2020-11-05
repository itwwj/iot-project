package com.github.iot.annotation;

import com.github.iot.entity.Pattern;
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
public @interface Topic {

    /**
     * topic
     * @return
     */
    String topic() default "";

    /**
     * qos
     * @return
     */
    int qos() default 0;

    /**
     * 订阅模式
     * @return
     */
    Pattern patten() default Pattern.NONE;

    /**
     * 共享订阅组
     * @return
     */
    String group() default "group1";
}