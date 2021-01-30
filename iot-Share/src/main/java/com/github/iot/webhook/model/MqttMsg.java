package com.github.iot.webhook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MqttMsg {
    /**
     * 用户名
     */
    private String username;
    /**
     * topic
     */
    private String topic;
    /**
     *  事件触发时间 (ms)
     */
    private Long timestamp;
    /**
     * qos
     */
    private Integer qos;
    /**
     * PUBLISH 消息到达 Broker 的时间 (ms)
     */
    private Long publish_received_at;
    /**
     * 客户端的 IPAddress
     */
    private String peerhost;
    /**
     * 消息内容
     */
    private String payload;
    /**
     * 节点
     */
    private String node;
    /**
     * 消息id
     */
    private String id;
    /**
     * 客户端id
     */
    private String clientid;

}
