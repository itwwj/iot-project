package com.github.iot.controller;

import com.github.iot.utils.PubMessageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jie
 */
@Slf4j
@Data
@RestController
public class PubController {


    @GetMapping("pub")
    public String pub(String topic,String message) throws Exception {
        PubMessageUtils.pub(topic, message);
        return "ok";
    }

}
