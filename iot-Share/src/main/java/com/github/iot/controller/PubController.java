package com.github.iot.controller;

import com.github.iot.api.PublishApi;
import com.github.iot.api.rest.BaseResoult;
import com.github.iot.api.rest.pub.Publish;
import com.github.iot.utils.PubMessageUtils;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jie
 */
@Data
@RestController
public class PubController {

    private final PublishApi publishApi;

    @GetMapping("pub")
    public String pub(String topic, String message) throws Exception {
        PubMessageUtils.pub(topic, message);
        return "ok";
    }

    @GetMapping("rrpc")
    public String rrpc(String topic, String message) throws Exception {
        MqttMessage rrpcMessage = PubMessageUtils.rrpcPub(topic, message, 8);
        return new String(rrpcMessage.getPayload());
    }

    @PostMapping("pub")
    public BaseEntity pub(@RequestBody Publish publish) throws Exception {
        return publishApi.pub(publish);

    }

}
