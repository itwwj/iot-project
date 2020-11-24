package com.github.iot.common;

import com.github.iot.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 封装的主题消费父类
 *
 * @author jie
 */
@Slf4j
public abstract class SuperConsumer<T> implements IMqttMessageListener, MsgDecoder<T> {
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        log.info("\r\n 收到主题 :\r\n" + topic + " 的消息:\r\n" + new String(mqttMessage.getPayload()));
        ThreadUtils.executorService.submit(() -> {
            try {
                T decoder = decoder(mqttMessage);
                msgHandler(topic, decoder);
            } catch (Exception ex) {
                //解决业务处理错误导致断线问题
                log.error(ex.toString());
            }
        });
    }

    /**
     * 业务操作
     *
     * @param topic
     * @param entity
     */
    protected abstract void msgHandler(String topic, T entity);
}
