package com.github.iot.common;

import cn.hutool.core.util.CharsetUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.iot.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 下位机通过mqtt消息查询数据库信息并同步响应
 * @author jie
 */
@Slf4j
public abstract class SuperSelectMysqlRRPC<T, M extends BaseMapper<T>> implements IMqttMessageListener, MsgEncoder<List<T>> {
    @Autowired
    protected M mapper;
    @Autowired
    private MqttClient client;

    /**
     * 查询消息的主题topic需先规定好 比如结尾是request 响应时为response
     * @param topic
     * @param mqttMessage
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.info("\r\n 收到主题 :\r\n" + topic + " 的消息:\r\n" + new String(mqttMessage.getPayload()));
        ThreadUtils.executorService.submit(() -> {
            try {
                Wrapper<T> wrapper = buildWrapper(topic, mqttMessage);
                List<T> objects = (List<T>) mapper.selectObjs(wrapper);
                String encoder = encoder(objects);
                client.publish(topic.replace("request", "response"), encoder.getBytes(CharsetUtil.UTF_8), mqttMessage.getQos(), false);
            } catch (Exception ex) {
                //解决业务处理错误导致断线问题
                log.error(ex.toString());
            }
        });
    }
    protected abstract Wrapper<T> buildWrapper(String topic, MqttMessage mqttMessage);
}
