package com.github.iot.connectconfig;

import com.github.iot.entity.Pattern;
import com.github.iot.entity.SubscriptTopic;
import com.github.iot.utils.ApplicationContextUtil;
import com.github.iot.utils.ThreadUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * mqtt回调类
 *
 * @author jie
 */
@Data
@Slf4j
public class MqttCallback implements MqttCallbackExtended {

    private final List<SubscriptTopic> topicMap;


    /**
     * 客户端断开后触发
     *
     * @param throwable 异常
     */
    @SneakyThrows
    @Override
    public void connectionLost(Throwable throwable) {
        MqttClient client = ApplicationContextUtil.getBean(MqttClient.class);
        MqttConnectOptions option = ApplicationContextUtil.getBean(MqttConnectOptions.class);
        while (!client.isConnected()) {
            log.info("emqx重新连接....................................................");
            client.connect(option);
            Thread.sleep(1000);
        }
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic   主题
     * @param message 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        for (SubscriptTopic subscriptTopic : topicMap) {
            if (subscriptTopic.getPattern() != Pattern.NONE && isMatched(subscriptTopic.getTopic(), topic)) {
                subscriptTopic.getMessageListener().messageArrived(topic, message);
                break;
            }
        }
    }

    /**
     * 检测一个主题是否为一个通配符表示的子主题
     *
     * @param topicFilter 通配符主题
     * @param topic       子主题
     * @return 是否为通配符主题的子主题
     */
    private boolean isMatched(String topicFilter, String topic) {
        return MqttTopic.isMatched(topicFilter, topic);
    }

    /**
     * 发布消息成功
     *
     * @param token token
     */
    @SneakyThrows
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        String[] topics = token.getTopics();
        for (String topic : topics) {
            log.info("向主题：" + topic + "发送数据：" + new String(token.getMessage().getPayload()));
        }
    }

    /**
     * 连接emq服务器后触发
     *
     * @param b
     * @param s
     */
    @SneakyThrows
    @Override
    public void connectComplete(boolean b, String s) {
        MqttClient client = ApplicationContextUtil.getBean(MqttClient.class);
        if (client.isConnected()) {
            for (SubscriptTopic sub : topicMap) {
                client.subscribe(sub.getSubTopic(), sub.getQos(), sub.getMessageListener());
                log.info("订阅主题:" + sub.getSubTopic());
            }
            log.info("共订阅:   " + topicMap.size() + "   个主题!");
        }
    }
}
