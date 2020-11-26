package com.gitee.test;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import com.github.iot.utils.ThreadUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * @author jie
 */
public class MqttTest {
    static final String broker = "tcp://192.168.1.177:1883";

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ThreadUtils.executorService.submit(() -> {
                try {
                    String clientId = UUID.randomUUID().toString();
                    MqttClient client = new MqttClient(broker, clientId);
                    MqttConnectOptions options = new MqttConnectOptions();
                    options.setUserName("root");
                    options.setPassword("root".toCharArray());
                    send(client, clientId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void send(MqttClient client, String msg) throws Exception {
        for (int i = 0; i < 100; i++) {
            client.publish("device/test", (msg + i).getBytes(CharsetUtil.UTF_8), 0, false);
            Thread.sleep(100);
        }
    }
}
