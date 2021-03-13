package com.gitee.test;

import cn.hutool.core.util.CharsetUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jie
 */
public class MqttSend {
    static final String broker = "tcp://192.168.1.177:1883";
    public static AtomicLong valueCount = new AtomicLong(0);
    public static void main(String[] args) throws MqttException, UnsupportedEncodingException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    connect(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        connect("main");
    }
public static Long time=0L;
    public static void connect(String message) throws InterruptedException, MqttException {
        MqttClient client = new MqttClient(broker, UUID.randomUUID().toString(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("root");
        options.setPassword("root".toCharArray());
        client.connect(options);

/*        for (int i=0;i<500000;i++){
            client.publish("device/123","axiba".getBytes(CharsetUtil.UTF_8),2,false);
            System.out.println("发送第"+i+"条");
            Thread.sleep(1);
        }*/
        Long i = 0L;

        while (true) {
            i++;
            Long finalI = i;
            long increment = valueCount.getAndIncrement();
            if (increment%1000==0){
                long now = System.currentTimeMillis();
                double s = (now - time) / 1000.000;
                time=now;
                System.out.println(increment+"               "+(s));
            }
            try {
                    client.publish("device/123",(message+ finalI).getBytes(CharsetUtil.UTF_8),0,false);
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            Thread.sleep(10);
        }
    }
}
