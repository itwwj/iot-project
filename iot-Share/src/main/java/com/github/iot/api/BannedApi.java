package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.banned.Sbanned;
import com.github.iot.api.restentity.banned.Gbanned;
import com.github.iot.api.restentity.banned.R;
import com.github.iot.api.restentity.banned.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 黑名单api
 * @author jie
 */
@Component
public class BannedApi extends BaseApi {

    private static final String SUFFIX = "/banned";

    /**
     * 获取黑名单列表
     *
     * @param page
     * @param size
     * @return
     */
    public Gbanned select(int page, int size) {
        HttpRequest httpRequest = HttpRequest.get(joint(url , SUFFIX , param(page, size)));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, Gbanned.class);
    }

    /**
     * 添加黑名单
     *
     * @param sbanned
     * @return
     */
    public R instert(Sbanned sbanned) {
        HttpRequest httpRequest = HttpRequest.post(joint(url , SUFFIX))
                .body(JSON.toJSONString(sbanned));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, R.class);
    }

    /**
     * 删除黑名单
     *
     * @param who
     * @param as
     * @return
     */
    public Result del(String as, String who) {
        HttpRequest httpRequest = HttpRequest.delete(joint(url , SUFFIX , StrUtil.SLASH , as , StrUtil.SLASH , who));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, Result.class);
    }
}
