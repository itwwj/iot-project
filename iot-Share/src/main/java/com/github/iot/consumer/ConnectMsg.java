package com.github.iot.consumer;

import com.alibaba.fastjson.JSON;
import com.github.iot.annotation.Topic;
import com.github.iot.entity.Connect;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Slf4j
@Topic(topic = "$SYS/brokers/+/clients/+/connected")
public class ConnectMsg implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("\r\n 收到主题 :\r\n" + topic + " 的消息:\r\n" + new String(message.getPayload()));
        Connect analysis = JSON.parseObject(new String(message.getPayload()), Connect.class);
        //接下来就是你自己的操作了
        //TODO 业务操作
    }
}
