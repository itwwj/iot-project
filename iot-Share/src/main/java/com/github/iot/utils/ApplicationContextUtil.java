package com.github.iot.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 类名称:ApplicationContextUtil
 * 类描述:
 * <p>
 * <p>从容器中获取bean
 * 创建时间:  2020/1/3
 */
@Component
public class ApplicationContextUtil {

    private static ApplicationContextUtil instance;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void ApplicationContextUtil() {
        instance = this;
    }

    /**
     * 根据字节码获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }

    /**
     * 根据名字获取bean
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException {
        return (T)instance.applicationContext.getBean(name);
    }

}
