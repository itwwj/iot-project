package com.github.iot.common;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
public interface MsgDecoder<T> {
    /**
     * 下位机消息解码器
     * @param msg
     * @return
     */
    T decoder(MqttMessage msg);
}
