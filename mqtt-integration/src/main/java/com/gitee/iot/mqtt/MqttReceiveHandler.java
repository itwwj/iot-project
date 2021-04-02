package com.gitee.iot.mqtt;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

/**
 * @author jie
 */
@Slf4j
@Configuration
public class MqttReceiveHandler {

    @Bean
    @ServiceActivator(inputChannel = "defaultMqttInputChannel")
    public MessageHandler defaultHandler() {
        return message -> {
            log.info(
                    "defaultTopicReceiver\nheader:{},\npayload:{}",
                    JSON.toJSONString(message.getHeaders(), true),
                    JSON.toJSONString(message.getPayload(), true)
            );
        };
    }
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            log.info(
                    "{}\nheader:{}, payload:{}",
                    message.getHeaders().get("mqtt_receivedTopic"),
                    JSON.toJSONString(message.getHeaders(), true),
                    JSON.toJSONString(message.getPayload(), true)
            );
        };
    }
}
