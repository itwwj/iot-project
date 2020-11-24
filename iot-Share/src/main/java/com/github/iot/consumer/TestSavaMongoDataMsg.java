package com.github.iot.consumer;

import com.alibaba.fastjson.JSON;
import com.github.iot.annotation.Topic;
import com.github.iot.common.SuperSavaMongoData;
import com.github.iot.entity.Pattern;
import com.github.iot.entity.TestMongoData;
import com.github.iot.mapper.TestMongoDataDao;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Topic(topic = "device/+/testmongo",patten = Pattern.SHARE)
public class TestSavaMongoDataMsg extends SuperSavaMongoData<TestMongoData,String, TestMongoDataDao> {
    /**
     * 在这里构建消息实体 因为下位机可能上传的不是json
     * 比如我们上传的16进制字符串就要在这里解析然后入库
     * 测试数据：
     * {
     *     "deviceName":"阿西吧"
     * }
     * @param msg
     * @return
     */
    @Override
    public TestMongoData decoder(MqttMessage msg) {
        return JSON.parseObject(new String(msg.getPayload()), TestMongoData.class);
    }
}
