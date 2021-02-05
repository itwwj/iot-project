package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.BaseResoult;
import com.github.iot.api.restentity.rules.RuleParameters;
import com.github.iot.api.restentity.rules.RuleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jie
 */
@Component
public class RulesApi extends BaseApi {

    private static final String SUFFIX = "/rules";

    /**
     * 根据id查询规则
     *
     * @param id
     * @return
     */
    public RuleResponse findById(String id) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, StrUtil.SLASH, id));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, RuleResponse.class);
    }

    /**
     * 插入规则
     *
     * @param rule
     * @return
     */
    public RuleResponse instert(RuleParameters rule) {
        HttpRequest httpRequest = HttpRequest.post(joint(url, SUFFIX)).body(JSON.toJSONString(rule));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, RuleResponse.class);
    }

    /**
     * 修改规则
     *
     * @param id
     * @param rule
     * @return
     */
    public RuleResponse updata(String id, RuleParameters rule) {
        HttpRequest httpRequest = HttpRequest.put(joint(url, SUFFIX, StrUtil.SLASH, id)).body(JSON.toJSONString(rule));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, RuleResponse.class);
    }

    /**
     * 删除规则
     *
     * @param id
     * @return
     */
    public BaseResoult del(String id) {
        HttpRequest httpRequest = HttpRequest.delete(joint(url, SUFFIX, StrUtil.SLASH, id));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BaseResoult.class);
    }
}
