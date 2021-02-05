package com.github.iot.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.api.restentity.client.ClientOne;
import com.github.iot.api.restentity.client.ClientPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.iot.api.restentity.BaseResoult;
/**
 * client操作api
 * @author jie
 */
@Component
public class ClientApi extends BaseApi {


    private static final String SUFFIX = "/clients";

    /**
     * 分页查询全部设备
     * @param page
     * @param size
     * @return
     */
    public ClientPage findPage(int page, int size) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, param(page, size)));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, ClientPage.class);
    }

    /**
     * 根据id查询设备
     * @param clientId
     * @return
     */
    public ClientOne findById(String clientId) {
        HttpRequest httpRequest = HttpRequest.get(joint(url, SUFFIX, StrUtil.SLASH, clientId));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, ClientOne.class);
    }

    /**
     * 根据clientId剔除设备
     * @param clientId
     * @return
     */
    public BaseResoult disClientByClientid(String clientId){
        HttpRequest httpRequest = HttpRequest.delete(joint(url, SUFFIX, StrUtil.SLASH, clientId));
        String body = getBody(httpRequest);
        return JSON.parseObject(body, BaseResoult.class);
    }
}
