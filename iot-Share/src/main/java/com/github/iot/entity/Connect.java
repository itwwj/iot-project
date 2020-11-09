package com.github.iot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p> 设备上线
 * 邮箱:275236367@qq.com
 * 创建时间:  2020/1/4
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Connect {
    /**
     * 客户端id
     */
    private String clientid;
    /**
     * 用户名
     */
    private String username;
    /**
     * ip地址
     */
    private String ipaddress;
    /**
     *连接回执
     */
    private int connack;
    /**
     * 事件触发时间 (ms)
     */
    private long ts;
    /**
     * 协议版本
     */
    private int proto_ver;
    /**
     * 协议名字
     */
    private String proto_name;
    /**
     * MQTT clean_start
     */
    private boolean clean_start;
    /**
     *保持连接
     */
    private int keepalive;
}
