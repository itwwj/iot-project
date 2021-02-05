package com.github.iot.api.restentity.pub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publish {
    /**
     * 主题
     */
    private String topic;
    /**
     * 消息正文
     */
    private String payload;
    /**
     * QoS 等级
     */
    private int qos;
    /**
     * 是否为保留消息
     */
    private boolean retain;
    /**
     * 客户端标识符
     */
    private String clientid;
}
