package com.github.iot.shadow.test;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.github.iot.annotation.Topic;
import com.github.iot.common.SupperRpcConsumer;
import com.github.iot.shadow.BaseShadow;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static com.github.iot.shadow.CacheShadow.deviceShadow;

/**
 * 设备拉取影子状态
 * @author jie
 */
//@Topic(topic = "shadow/request/loght/+", qos = 2)
public class PullStatus extends SupperRpcConsumer<Light, BaseShadow<Light>> {
    @Override
    protected BaseShadow<Light> msgHandler(String topic, Light entity) {
        String clientId = topic.substring(topic.lastIndexOf("/")).replace("/", "");
        return deviceShadow.get(clientId);
    }

    @Override
    public Light decoder(MqttMessage msg) {
        return JSON.parseObject(new String(msg.getPayload()), Light.class);
    }

    @SneakyThrows
    @Override
    public byte[] encoder(BaseShadow<Light> baseShadow) {
        return JSON.toJSONString(baseShadow).getBytes(CharsetUtil.UTF_8);
    }
}
