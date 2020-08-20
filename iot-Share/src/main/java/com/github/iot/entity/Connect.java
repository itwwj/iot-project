package com.github.iot.entity;

import lombok.Data;

/**
 * <p> 设备上线
 * 邮箱:275236367@qq.com
 * 创建时间:  2020/1/4
 * @author jie
 */
@Data
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
     *
     */
    private long ts;
    /**
     *
     */
    private int proto_ver;
    /**
     *
     */
    private String proto_name;
    /**
     *
     */
    private boolean clean_start;
    /**
     *保持连接
     */
    private int keepalive;
}
