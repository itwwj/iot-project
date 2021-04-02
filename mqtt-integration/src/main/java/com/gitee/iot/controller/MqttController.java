package com.gitee.iot.controller;

import com.gitee.iot.mqtt.MqttSendHandler;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@Data
@RestController()
@RequestMapping("mqtt")
public class MqttController {
    private final MqttSendHandler sendHandler;


    @GetMapping("/send")
    public void send(String data) {
        sendHandler.sendToMqtt(data);
    }
}
