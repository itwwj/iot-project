package com.github.iot.api;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.pub.Publish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.iot.api.restentity.BaseResoult;
/**
 * @author jie
 */
@Component
public class PublishApi extends BaseApi {

    private static final String SUFFIX = "/mqtt/publish";

    /**
     * 发布消息
     * @param publish
     * @return
     */
    public BaseResoult pub(Publish publish) {
        HttpRequest httpRequest = HttpRequest.post(joint(url, SUFFIX)).body(JSON.toJSONString(publish));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BaseResoult.class);
    }
}
