package com.github.iot.common;

import com.github.iot.utils.PubMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 同步回调
 * @author jie
 */
@Slf4j
public abstract class SupperRpcConsumer<D, E> implements IMqttMessageListener, MsgEncoder<E>, MsgDecoder<D> {
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        log.info("\r\n 收到主题 :\r\n" + topic + "  qos=" + mqttMessage.getQos() + " 的消息:\r\n" + new String(mqttMessage.getPayload()));
        ThreadUtils.executorService.submit(() -> {
            try {
                D decoder = decoder(mqttMessage);
                E e = msgHandler(topic, decoder);
                byte[] encoder = encoder(e);
                PubMessageUtils.pub(topic.replace("request","response"),encoder,2);
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
    protected abstract E msgHandler(String topic, D entity);
}
