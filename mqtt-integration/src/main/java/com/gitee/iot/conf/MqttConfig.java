package com.gitee.iot.conf;

import com.gitee.iot.property.MqttProperty;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author jie
 */
@Configuration
@IntegrationComponentScan
@EnableConfigurationProperties(MqttProperty.class)
public class MqttConfig {
    @Autowired
    private MqttProperty mqttProperty;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.default.topic}")
    private String defaultTopic;

    @Value("${mqtt.default.qos}")
    private Integer defaultQos;

    /**
     * mqtt broker连接参数
     *
     * @return
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperty.getUsername());
        mqttConnectOptions.setPassword(mqttProperty.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{mqttProperty.getUrl()});
        mqttConnectOptions.setKeepAliveInterval(mqttProperty.getKeepAlive());
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel defaultMqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 说明：
     * ConditionalOnProperty(value = "driver.mqtt.default.receive.enable")
     * 根据配置属性driver.mqtt.default.receive.enable选择是否开启 Default Topic 主题的数据接收逻辑
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "mqtt.default.receive.enable")
    public MessageProducer defaultInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId + "_default_inbound_",
                mqttClientFactory(),
                defaultTopic
        );
        adapter.setCompletionTimeout(mqttProperty.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(defaultQos);
        adapter.setOutputChannel(defaultMqttInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId + "_inbound",
                mqttClientFactory(),
                mqttProperty.getTopics().toArray(new String[0])
        );
        adapter.setCompletionTimeout(mqttProperty.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(mqttProperty.getQos().stream().mapToInt(Integer::valueOf).toArray());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel mqttOutChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(defaultQos);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }
}
