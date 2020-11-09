package com.github.iot.controller;

import com.github.iot.entity.webhook.MqttMsg;
import com.github.iot.entity.webhook.WebHookConnect;
import com.github.iot.entity.webhook.WebHookDisconnect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * emq规则引擎测试web接口
 * @author jie
 */
@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebHookController {
    /**
     * 规则：SELECT * FROM "$events/client_connected"
     * @param msg
     */
    @RequestMapping("/connect")
    public void connect(@RequestBody WebHookConnect msg){
        //接下来就是你自己的操作了
        //TODO 业务操作
        log.info(msg.toString());
    }
    /**
     * 规则：SELECT * FROM "$events/client_disconnected"
     * @param msg
     */
    @RequestMapping("/disconnect")
    public void disconnect(@RequestBody WebHookDisconnect msg){
        //接下来就是你自己的操作了
        //TODO 业务操作
        log.info(msg.toString());
    }
    /**
     * 规则：SELECT * FROM "device/#"
     * @param msg
     */
    @RequestMapping("/device")
    public void device(@RequestBody MqttMsg msg){
        //接下来就是你自己的操作了
        //TODO 业务操作
        log.info(msg.toString());
    }
}
