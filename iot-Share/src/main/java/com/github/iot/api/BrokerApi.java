package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.broker.BrokersResponse;
import com.github.iot.api.restentity.broker.BrokersResponseList;
import org.springframework.stereotype.Component;

/**
 * @author jie
 */
@Component
public class BrokerApi extends BaseApi {

    private static final String SUFFIX = "/brokers";

    /**
     * 查询集群下所有节点信息
     *
     * @return
     */
    public BrokersResponseList findBrokers() {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BrokersResponseList.class);
    }

    /**
     * 查询指定节点信息
     *
     * @return
     */
    public BrokersResponse findBroker(String node) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, StrUtil.SLASH, node));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BrokersResponse.class);
    }
}
