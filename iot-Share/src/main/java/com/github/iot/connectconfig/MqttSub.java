package com.github.iot.connectconfig;

import com.github.iot.annotation.MyTopicMap;
import com.github.iot.entity.SubscriptTopic;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 连接 订阅类
 * @author jie
 */
@Slf4j
@Component
public class MqttSub implements CommandLineRunner {

    private EmqKeeper emqKeeper;
    /**
     * 主题和消费类存入集合断线重连使用
     */
    private List<SubscriptTopic> topicMap = new ArrayList<SubscriptTopic>();

    @Autowired
    private ApplicationContext applicationContext;

    public MqttSub(EmqKeeper emqKeeper) {
        this.emqKeeper = emqKeeper;
    }

    @Override
    public void run(String... args) throws Exception {
        //得到所有使用MyTopicMap注解的类
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyTopicMap.class);
        for (String className : beansWithAnnotation.keySet()) {
            Class<?> classByteCode = beansWithAnnotation.get(className).getClass();
            //获取类的注解属性
            MyTopicMap annotation = AnnotationUtils.findAnnotation(classByteCode, MyTopicMap.class);
            String topic = annotation.topic();
            int qos = annotation.qos();
            topicMap.add(new SubscriptTopic(topic, qos, (IMqttMessageListener) applicationContext.getBean(classByteCode)));
        }
        //连接mqtt服务器
        emqKeeper.connetToServer(topicMap);

        log.info("===================开始订阅主题===================");
        for (SubscriptTopic sub : topicMap) {
            emqKeeper.subscript(sub.getTopic(), sub.getQos(), sub.getMessageListener());
            log.info("订阅主题:"+ sub.getTopic());
        }
        log.info("=====================订阅结束=====================");
        log.info("共订阅:   "+topicMap.size()+"   个主题!");
    }
}