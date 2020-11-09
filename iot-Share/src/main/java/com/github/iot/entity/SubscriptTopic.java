package com.github.iot.entity;

import lombok.*;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

/**
 * @author jie
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptTopic {
    /**
     * 原主题
     */
    private String topic;
    /**
     * 订阅主题
     */
    private String subTopic;
    /**
     * 订阅模式
     */
    private Pattern pattern;
    /**
     * 消息等级
     */
    private int qos;
    /**
     * 消费类
     */
    private IMqttMessageListener messageListener;

}
