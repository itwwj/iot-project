package com.github.iot.consumer;

import com.github.iot.annotation.MyTopicMap;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Slf4j
@MyTopicMap(topic = "$share/group1/device/+/test", qos = 0)
public class TestDevice implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("\r\n 收1到主题 :\r\n" + topic + " 的消息:\r\n" + new String(message.getPayload()));
        //TODO 业务操作
    }
}
