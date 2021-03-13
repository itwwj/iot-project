package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.node.NodeResponse;
import com.github.iot.api.restentity.node.NodeResponseList;
import org.springframework.stereotype.Component;

/**
 * @author jie
 */
@Component
public class NodesApi extends BaseApi {

    private static final String SUFFIX = "/nodes";

    /**
     * 查询集群下所有节点信息
     *
     * @return
     */
    public NodeResponseList findNodes() {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, NodeResponseList.class);
    }

    /**
     * 查询指定集群节点信息
     *
     * @return
     */
    public NodeResponse findNode(String node) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, StrUtil.SLASH, node));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, NodeResponse.class);
    }
}
