package com.github.iot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

/**
 * @author jie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
