package com.github.iot.rrpc;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.iot.annotation.Topic;
import com.github.iot.common.SuperConsumer;
import com.github.iot.utils.PubMessageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


/**
 * 注意！topic必须按照这个格式回复
 * rpc/clientId/response/messageId
 *
 * @author jie
 */

@Data
@Slf4j
//@Topic(topic = "rpc/+/response/+",qos = 2)
public class RrpcConsumer extends SuperConsumer<MqttMessage> {

    private final StringRedisTemplate redisTemplate;

    @Override
    protected void msgHandler(String topic, MqttMessage entity) {
        int i = topic.lastIndexOf("/");
        String messageId = topic.substring(i).replace("/", "");
        Boolean exists = redisTemplate.hasKey(messageId);
        if (exists) {
            redisTemplate.opsForValue().set(messageId, JSON.toJSONString(entity));
            redisTemplate.expire(messageId, 20, TimeUnit.SECONDS);
        }
    }

    @Override
    public MqttMessage decoder(MqttMessage msg) {
        return msg;
    }

    public MqttMessage rrpcPub(String topic, int qos, byte[] message, long timeout) throws Exception {
        if (timeout < 0 || timeout > 10) {
            throw new Exception("timeout 参数不合法");
        }
        String messageId = UUID.randomUUID().toString();
        topic = topic + "/" + messageId;
        PubMessageUtils.pub(topic, message, qos);
        redisTemplate.opsForValue().set(messageId, StrUtil.EMPTY);
        redisTemplate.expire(messageId, timeout, TimeUnit.SECONDS);
        while (true) {
            Boolean exists = redisTemplate.hasKey(messageId);
            if (!exists) {
                throw new Exception("超时");
            }
            String s = redisTemplate.opsForValue().get(messageId);
            if (!StrUtil.isEmptyIfStr(s)) {
                return JSON.parseObject(s, MqttMessage.class);
            }
            Thread.sleep(8);
        }
    }
}