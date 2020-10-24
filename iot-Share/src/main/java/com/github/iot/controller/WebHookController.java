package com.github.iot.controller;

import com.github.iot.entity.Connect;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @RequestMapping("/connect")
    public void connect(@RequestBody Connect msg){
        //接下来就是你自己的操作了
        //TODO 业务操作
    }
}
