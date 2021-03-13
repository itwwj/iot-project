package com.github.iot.shadow.test;

import com.alibaba.fastjson.JSON;
import com.github.iot.shadow.BaseShadow;
import com.github.iot.shadow.State;
import com.github.iot.utils.PubMessageUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.github.iot.shadow.CacheShadow.deviceShadow;

/**
 * @author jie
 */
@Component
public class UpdateLightStatus {

    public BaseShadow<Light> update(Light light, String clientId) throws Exception {
        if (!deviceShadow.containsKey(clientId)) {
            BaseShadow<Light> shadow = new BaseShadow<>();
            State<Light> state = new State<>();
            state.setDesired(light);
            shadow.setState(state);
            shadow.setVersion(-1L);
            shadow.setTimestamp(LocalDateTime.now());
            deviceShadow.put(clientId, shadow);
        } else {
            BaseShadow<Light> baseShadow = deviceShadow.get(clientId);
            State<Light> state = baseShadow.getState();
            state.setDesired(light);
            baseShadow.setState(state);
            deviceShadow.put(clientId, baseShadow);
        }
        MqttMessage mqttMessage = PubMessageUtils.rrpcPub("shadow/get/" + clientId + "/lightbulb", 2, JSON.toJSONString(deviceShadow.get(clientId)), 8);

        BaseShadow<Light> baseShadow = JSON.parseObject(new String(mqttMessage.getPayload()), BaseShadow.class);
        if (baseShadow.getVersion()== deviceShadow.get(clientId).getVersion()+1) {
            BaseShadow<Light> baseShadow1 = deviceShadow.get(clientId);
            State<Light> state = baseShadow1.getState();
            state.setReported(baseShadow.getState().getDesired());
            baseShadow1.setState(state);

        }
        return null;
    }
}
