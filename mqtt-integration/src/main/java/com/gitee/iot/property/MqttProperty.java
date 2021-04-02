package com.gitee.iot.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author jie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("mqtt")
public class MqttProperty {
    private String username;

    private String password;

    private String url;

    private List<Integer> qos;

    private Integer keepAlive;

    private Integer completionTimeout;

    private List<String> topics;
}
