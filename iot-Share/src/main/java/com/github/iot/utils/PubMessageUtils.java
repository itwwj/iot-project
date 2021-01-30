package com.github.iot.utils;

import cn.hutool.core.util.CharsetUtil;
import com.github.iot.rrpc.RrpcConsumer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

/**
 * 发布消息工具类
 * 注意，直接注入client pub会报错 报错原因我也不知道 如果有大佬知道就请留言告知一下
 *
 * @author jie
 */
@Slf4j
public class PubMessageUtils {

    public static boolean pub(String topic, String message) throws MqttException, UnsupportedEncodingException {
        return pub(topic, message.getBytes(CharsetUtil.UTF_8), 0, false);
    }

    public static boolean pub(String topic, String message, int qos) throws MqttException, UnsupportedEncodingException {
        return pub(topic, message.getBytes(CharsetUtil.UTF_8), qos, false);
    }

    public static boolean pub(String topic, byte[] message) throws MqttException {
        return pub(topic, message, 0, false);
    }

    public static boolean pub(String topic, byte[] message, int qos) throws MqttException {
        return pub(topic, message, qos, false);
    }

    public static boolean pub(String topic, byte[] message, int qos, boolean retained) throws MqttException {
        try {
            MqttClient client = ApplicationContextUtil.getBean(MqttClient.class);
            client.publish(topic, message, qos, retained);
        } catch (MqttException e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    public static MqttMessage rrpcPub(String topic, String msg, long timeout) throws Exception {
        RrpcConsumer rrpc = ApplicationContextUtil.getBean(RrpcConsumer.class);
        return rrpc.rrpcPub(topic, 0, msg.getBytes(CharsetUtil.UTF_8), timeout);
    }

    public static MqttMessage rrpcPub(String topic, int qos, String msg, long timeout) throws Exception {
        RrpcConsumer rrpc = ApplicationContextUtil.getBean(RrpcConsumer.class);
        return rrpc.rrpcPub(topic, qos, msg.getBytes(CharsetUtil.UTF_8), timeout);
    }

    public static MqttMessage rrpcPub(String topic, byte[] msg, long timeout) throws Exception {
        RrpcConsumer rrpc = ApplicationContextUtil.getBean(RrpcConsumer.class);
        return rrpc.rrpcPub(topic, 0, msg, timeout);
    }

    public static MqttMessage rrpcPub(String topic, int qos, byte[] msg, long timeout) throws Exception {
        RrpcConsumer rrpc = ApplicationContextUtil.getBean(RrpcConsumer.class);
        return rrpc.rrpcPub(topic, qos, msg, timeout);
    }
}
