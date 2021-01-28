package com.github.iot.api;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.iot.entity.rest.request.Sbanned;
import com.github.iot.entity.rest.response.Gbanned;
import com.github.iot.entity.rest.response.R;
import com.github.iot.entity.rest.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


/**
 * @author jie
 */
@Component
public class BannedApi {
    @Value("${iot.api.url}")
    private String url;
    @Value("${iot.api.id}")
    private String id;
    @Value("${iot.api.password}")
    private String password;

    /**
     * 获取黑名单列表
     *
     * @param page
     * @param size
     * @return
     */
    public Gbanned getBanneds(int page, int size) {
        String body = HttpRequest.get(url + "/banned" + param(page, size))
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .basicAuth(id, password).execute().body();
        return JSON.parseObject(body, Gbanned.class);
    }

    /**
     * 添加黑名单
     *
     * @param sbanned
     * @return
     */
    public R setBanned(Sbanned sbanned) {
        String body = HttpRequest.post(url + "/banned")
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(JSON.toJSONString(sbanned))
                .basicAuth(id, password).execute().body();
        return JSON.parseObject(body, R.class);
    }

    /**
     * 删除黑名单
     * @param who
     * @param as
     * @return
     */
    public Result delBanned(String as, String who) {
        String body = HttpRequest.delete(url + "/banned/" + as + "/" + who)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .basicAuth(id, password).execute().body();
        return JSON.parseObject(body, Result.class);
    }

    private String param(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        if (size < 1 || size > 10000) {
            size = 10;
        }
        return "?_page=" + page + "&_limit=" + size;
    }
}
