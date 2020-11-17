package com.github.iot.connectconfig;

import com.github.iot.annotation.Topic;
import com.github.iot.entity.Pattern;
import com.github.iot.entity.EmqProperties;
import com.github.iot.entity.SubscriptTopic;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * mqttclient 工具类
 *
 * @author jie
 */
@Data
@Slf4j
@Component
public class EmqKeeper implements CommandLineRunner {

    @Value("${server.port}")
    private int port;

    @Autowired
    private EmqProperties emqProperties;

    @Autowired
    private ApplicationContext applicationContext;

    private MqttClient client;
    MqttConnectOptions options;

    private List<SubscriptTopic> topicMap = new ArrayList<SubscriptTopic>();
    @Override
    public void run(String... args) throws Exception {
        client = new MqttClient(emqProperties.getBroker(), Inet4Address.getLocalHost().getHostAddress() + ":" + port, new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        options.setUserName(emqProperties.getUserName());
        options.setPassword(emqProperties.getPassword().toCharArray());
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(emqProperties.getCleanSession());
        //断线重连
        options.setAutomaticReconnect(emqProperties.getReconnect());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(emqProperties.getTimeout());
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*10秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(emqProperties.getKeepAlive());

        //得到所有使用@Topic注解的类
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Topic.class);
        for (String className : beansWithAnnotation.keySet()) {
            Class<?> classByteCode = beansWithAnnotation.get(className).getClass();
            //获取类的注解属性
            Topic annotation = AnnotationUtils.findAnnotation(classByteCode, Topic.class);
            String topic = annotation.topic();
            int qos = annotation.qos();
            Pattern patten = annotation.patten();
            String group = annotation.group();
            String subTopic=topic;
            if (patten==Pattern.SHARE){
                subTopic="$share/"+group+"/"+topic;
            }else if (patten==Pattern.QUEUE){
                subTopic="$queue/"+topic;
            }
            topicMap.add(new SubscriptTopic(topic,subTopic,patten, qos, (IMqttMessageListener) applicationContext.getBean(classByteCode)));
        }
        this.client.setCallback(new CallbackOrListener(topicMap));
        connetToServer();
    }

    /**
     * 客户端连接服务端
     *
     */
    public void connetToServer() throws Exception {

        try {
            if (!client.isConnected()) {
                client.connect(options);
            }
        } catch (Exception e) {
            log.error("连接到emq失败;" + emqProperties.getBroker());
            log.error(e.toString());
        }
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
        client.subscribe(topic, qos, messageListener);
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
        MqttTopic mqttTopic = client.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }
}
