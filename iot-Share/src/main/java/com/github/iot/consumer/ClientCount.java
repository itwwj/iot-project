package com.github.iot.consumer;

import com.github.iot.annotation.Topic;
import com.github.iot.common.SuperConsumer;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 监听在线设备总数
 * @author jie
 */
@Topic(topic = "$SYS/brokers/+/stats/connections.count")
public class ClientCount extends SuperConsumer<String> {
    @Override
    protected void msgHandler(String topic, String entity) {

    }

    @Override
    public String decoder(MqttMessage msg) {
        return new String(msg.getPayload());
    }
}
