package com.github.iot.api;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

/**
 * @author jie
 */
public abstract class BaseApi {
    @Value("${iot.api.url}")
    protected String url;
    @Value("${iot.api.username}")
    protected String username;
    @Value("${iot.api.password}")
    protected String password;

    /**
     * 分页参数封装到url
     *
     * @param page
     * @param size
     * @return
     */
    protected String param(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        if (size < 1 || size > 10000) {
            size = 10;
        }
        return "?_page=" + page + "&_limit=" + size;
    }

    /**
     * 发送http请求获取body
     *
     * @param httpRequest
     * @return
     */
    protected String getBody(HttpRequest httpRequest) {
        return httpRequest.header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .basicAuth(username, password).execute().body();
    }

    /**
     * url拼接
     *
     * @param args
     * @return
     */
    protected String joint(String... args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }
        return builder.toString();
    }
}
