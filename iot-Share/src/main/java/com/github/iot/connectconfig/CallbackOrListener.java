package com.github.iot.connectconfig;

import com.github.iot.entity.SubscriptTopic;
import com.github.iot.utils.ApplicationContextUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import java.util.List;

/**
 * 回调类
 * @author jie
 */
@Slf4j
public class CallbackOrListener implements MqttCallback {


    private List<SubscriptTopic> topicMap;

    public CallbackOrListener(List<SubscriptTopic> topicMap) {
        this.topicMap = topicMap;
    }

    /**
     * 客户端断开后触发
     *
     * @param throwable 异常
     */
    @SneakyThrows
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("EMQ连接断开....................................................");
        //尝试重新连接，
        for (int i = 0; i < 10; i++) {

            log.info("第 " + i + " 次尝试重新连接.");
            EmqKeeper emqKeeper = ApplicationContextUtil.getBean(EmqKeeper.class);

            if (!emqKeeper.getMqttClient().isConnected())
            {
                emqKeeper.connetToServer(topicMap);
            }

            if (emqKeeper.getMqttClient().isConnected())
            {
                break;
            }
            try {
                Thread.sleep(10L * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic   主题
     * @param message 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            // subscribe后得到的消息会执行到这里面
            log.info("message id             : " + message.getId());
            log.info("message topic          : " + topic);
            log.info("message Qos            : " + message.getQos());
            log.info("message Payload        : " + new String(message.getPayload()));

            for (SubscriptTopic subscriptTopic : topicMap) {
                if (isMatched(subscriptTopic.getTopic(), topic)) {
                    subscriptTopic.getMessageListener().messageArrived(topic, message);
                    break;
                }
            }
        } catch (Exception e) {
            //报错后断掉的问题，临时将错误吃掉。
            e.printStackTrace();
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
        //检测是否以指定前缀开始
        if (topicFilter.startsWith("$queue/")) {
            //替换前缀
            topicFilter = topicFilter.replaceFirst("\\$queue/", "");
        } else if (topicFilter.startsWith("$share/")) {
            topicFilter = topicFilter.replaceFirst("\\$share/", "");
            topicFilter = topicFilter.substring(topicFilter.indexOf('/') + 1);
        }
        return MqttTopic.isMatched(topicFilter, topic);
    }

    /**
     * 发布消息成功
     *
     * @param token token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete---------" + token.isComplete());
    }
}
