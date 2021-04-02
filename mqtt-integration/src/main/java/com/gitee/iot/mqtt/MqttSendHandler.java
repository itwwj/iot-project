package com.gitee.iot.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author jie
 */
@MessagingGateway(defaultRequestChannel = "mqttOutChannel")
public interface MqttSendHandler {
    /**
     * 使用 Default Topic & Default Qos 发送数据
     *
     * @param data string
     */
    void sendToMqtt(String data);

    /**
     * 使用 Default Topic & 自定义 Qos 发送数据
     *
     * @param qos  自定义 Qos
     * @param data string
     */
    void sendToMqtt(@Header(MqttHeaders.QOS) Integer qos, String data);

    /**
     * 使用 自定义 Topic & Default Qos 发送数据
     *
     * @param topic 自定义 Topic
     * @param data  string
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);

    /**
     * 使用 自定义 Topic & 自定义 Qos 发送数据
     *
     * @param topic 自定义 Topic
     * @param qos   自定义 Qos
     * @param data  string
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) Integer qos, String data);
}
