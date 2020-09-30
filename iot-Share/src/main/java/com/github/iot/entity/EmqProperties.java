package com.github.iot.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 配置类
 * @author jie
 */
@Data
@Component
@ConfigurationProperties(prefix = "emqx")
public class EmqProperties {
    /**
     * emq服务器地址
     */
    private String broker;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
     */
    private Boolean cleanSession;

}
