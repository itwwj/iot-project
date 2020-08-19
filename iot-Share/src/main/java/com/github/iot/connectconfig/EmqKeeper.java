package com.github.iot.connectconfig;

import com.github.iot.entity.EmqProperties;
import com.github.iot.entity.SubscriptTopic;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jie
 */
@Slf4j
@Component
public class EmqKeeper {
    private MqttClient client;
    private MqttConnectOptions options;
    private EmqProperties emqProperties;

    public EmqKeeper(EmqProperties emqProperties) throws Exception {
        this.emqProperties = emqProperties;
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(emqProperties.getBroker(), emqProperties.getClientId(), new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        options.setUserName(emqProperties.getUserName());
        options.setPassword(emqProperties.getPassword().toCharArray());
        options.setAutomaticReconnect(true);
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(emqProperties.getCleanSession());
        //断线重连
        options.setAutomaticReconnect(true);
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(20);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*10秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(10);
        // options.setAutomaticReconnect();
    }

    /**
     * 客户端连接服务端
     *
     * @param topicMap 订阅主题和消费类集合
     */
    public void connetToServer(List<SubscriptTopic> topicMap) {
        try {
            // 设置回调
            this.getMqttClient().setCallback(new CallbackOrListener(topicMap));
            if (!client.isConnected()) {
                client.connect(options);
            }
        } catch (MqttException e) {
            log.info("连接到emq失败;" + emqProperties.getBroker());
            e.printStackTrace();
        }
    }

    /**
     * 获取客户端
     *
     * @return 客户端
     */
    public MqttClient getMqttClient() {
        return client;
    }

    /**
     * 订阅主题
     *
     * @param topic           主题
     * @param qos             消息等级
     * @param messageListener 消费类
     * @throws Exception 异常
     */
    public void subscript(String topic, int qos, IMqttMessageListener messageListener) throws Exception {
        getMqttClient().subscribe(topic, qos, messageListener);
    }

    /**
     * 向某个主题发布消息
     *
     * @param topic: 发布的主题
     * @param msg:   发布的消息
     * @param qos:   消息质量    Qos：0、1、2
     */
    public void pubMsg(String topic, String msg, int qos) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = getMqttClient().getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }
}
