package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.BaseResoult;
import com.github.iot.api.restentity.resources.ResourceParameters;
import com.github.iot.api.restentity.resources.ResourceResponse;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author jie
 */
public class ResourcesApi extends BaseApi {

    private static final String SUFFIX = "/resources";

    /**
     * 根据id查找资源
     * @param id
     * @return
     */
    public ResourceResponse findById(String id) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, StrUtil.SLASH, id));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, ResourceResponse.class);
    }

    /**
     * 插入资源
     * @param resources
     * @return
     */
    public ResourceResponse instert(ResourceParameters resources) {
        HttpRequest httpRequest = HttpRequest.post(joint(url, SUFFIX)).body(JSON.toJSONString(resources));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, ResourceResponse.class);
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    public BaseResoult del(String id) {
        HttpRequest httpRequest = HttpRequest.delete(joint(url, SUFFIX, StrUtil.SLASH, id));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BaseResoult.class);
    }
}
