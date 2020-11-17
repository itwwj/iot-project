package com.github.iot.connectconfig;

import com.github.iot.entity.Pattern;
import com.github.iot.entity.SubscriptTopic;
import com.github.iot.utils.ApplicationContextUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 回调类
 *
 * @author jie
 */
@Slf4j
public class CallbackOrListener implements MqttCallbackExtended {

    private List<SubscriptTopic> topicMap;

    public CallbackOrListener(List<SubscriptTopic> topicMap) {
        this.topicMap = topicMap;
    }

    /**
     * 线程池
     */
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * 客户端断开后触发
     *
     * @param throwable 异常
     */
    @SneakyThrows
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("EMQ连接断开....................................................");
        EmqKeeper bean = ApplicationContextUtil.getBean(EmqKeeper.class);
        while (!bean.getClient().isConnected()) {
            log.info("emqx重新连接。。。。");
            bean.connetToServer();
            Thread.sleep(10000);
        }
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic   主题
     * @param message 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage message){
        for (SubscriptTopic subscriptTopic : topicMap) {
            if (subscriptTopic.getPattern() != Pattern.NONE && isMatched(subscriptTopic.getTopic(), topic)) {
                executorService.submit(() -> {
                    try {
                        subscriptTopic.getMessageListener().messageArrived(topic, message);
                    } catch (Exception e) {
                        //报错后断掉的问题，临时将错误吃掉。
                        e.printStackTrace();
                    }
                });
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
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete---------" + token.isComplete());
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
        EmqKeeper emqKeeper = ApplicationContextUtil.getBean(EmqKeeper.class);
        if (emqKeeper.getClient().isConnected()) {
            log.info("===================开始订阅主题===================");
            for (SubscriptTopic sub : topicMap) {
                emqKeeper.subscript(sub.getSubTopic(), sub.getQos(), sub.getMessageListener());
                log.info("订阅主题:" + sub.getSubTopic());
            }
            log.info("=====================订阅结束=====================");
            log.info("共订阅:   " + topicMap.size() + "   个主题!");
        }
    }
}
