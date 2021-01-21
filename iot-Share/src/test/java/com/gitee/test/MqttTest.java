package com.gitee.test;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import com.github.iot.utils.ThreadUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author jie
 */
public class MqttTest {
    static final String broker = "tcp://60.205.246.75:1883";

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i < 500000; i++) {
            String clientId = "device" + i;

            try {
                MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
                MqttConnectOptions options = new MqttConnectOptions();
                options.setUserName("root");
                options.setPassword("root".toCharArray());
                client.connect(options);
                // send(client, clientId);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            //    System.out.println(clientId + "结束发送");
            }
            System.out.println("连接第"+i);
            Thread.sleep(10);
        }
    }

    public static void send(MqttClient client, String msg) throws Exception {
        // client.subscribe("device/" + msg);
        for (int i = 0; i < 1000; i++) {
            client.publish("device/" + msg, (msg).getBytes(CharsetUtil.UTF_8), 0, false);
            Thread.sleep(10);
        }
    }
}
