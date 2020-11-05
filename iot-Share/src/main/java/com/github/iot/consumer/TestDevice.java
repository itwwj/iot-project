package com.github.iot.consumer;

import com.github.iot.annotation.Topic;
import com.github.iot.entity.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Slf4j
@Topic(topic = "device/+/test", qos = 0,patten = Pattern.SHARE)
public class TestDevice implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("\r\n 收到主题 :\r\n" + topic + " 的消息:\r\n" + new String(message.getPayload()));
        //TODO 业务操作
    }
}
