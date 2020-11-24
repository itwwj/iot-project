package com.github.iot.connectconfig;

import com.github.iot.annotation.Topic;
import com.github.iot.entity.Pattern;
import com.github.iot.entity.EmqProperties;
import com.github.iot.entity.SubscriptTopic;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * mqtt配置类
 *
 * @author jie
 */
@Slf4j
@Configuration
@AutoConfigureAfter(EmqProperties.class)
public class EmqConfig {

    @Value("${server.port}")
    private int port;


    /**
     * MQTT的连接设置
     *
     * @param emqProperties
     * @return
     */
    @Bean
    public MqttConnectOptions getOption(EmqProperties emqProperties) {
        MqttConnectOptions options = new MqttConnectOptions();
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
        return options;
    }

    @Bean
    public MqttClient getClient(MqttConnectOptions options, EmqProperties emqProperties, ApplicationContext applicationContext) throws Exception {
        List<SubscriptTopic> topicMap = new ArrayList<SubscriptTopic>();
        MqttClient client = new MqttClient(emqProperties.getBroker(), Inet4Address.getLocalHost().getHostAddress() + ":" + port, new MemoryPersistence());
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
            String subTopic = topic;
            if (patten == Pattern.SHARE) {
                subTopic = "$share/" + group + "/" + topic;
            } else if (patten == Pattern.QUEUE) {
                subTopic = "$queue/" + topic;
            }
            topicMap.add(new SubscriptTopic(topic, subTopic, patten, qos, (IMqttMessageListener) applicationContext.getBean(classByteCode)));
        }
        client.setCallback(new MqttCallback(topicMap));
        client.connect(options);
        return client;
    }
}
