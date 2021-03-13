package com.github.iot.shadow.test;

import com.alibaba.fastjson.JSON;
import com.github.iot.annotation.Topic;
import com.github.iot.common.SupperRpcConsumer;
import com.github.iot.shadow.BaseShadow;
import com.github.iot.shadow.CacheShadow;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 设备改变状态
 *
 * @author jie
 */
@Topic(topic = "shadow/update/loght/+", qos = 2)
public class LightShadow extends SupperRpcConsumer<BaseShadow<Light>, BaseShadow<Light>> {

    @Override
    public BaseShadow<Light> decoder(MqttMessage msg) {
        return JSON.parseObject(new String(msg.getPayload()), BaseShadow.class);
    }


    @Override
    public byte[] encoder(BaseShadow<Light> lightBaseShadow) {
        return new byte[0];
    }

    @Override
    protected BaseShadow<Light> msgHandler(String topic, BaseShadow<Light> entity) {
        String clientId = topic.substring(topic.lastIndexOf("/")).replace("/", "");
        BaseShadow<Light> shadow = CacheShadow.deviceShadow.get(clientId);


        CacheShadow.deviceShadow.put(clientId, entity);
        return null;
    }
}
