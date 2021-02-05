package com.github.iot.api.restentity.banned;

import lombok.Builder;
import lombok.Data;

/**
 * @author jie
 */
@Data
@Builder
public class Sbanned {
    /**
     * 添加至黑名单的对象，可以是客户端标识符、用户名和 IP 地址
     */
    private String who;
    /**
     * 用于区分黑名单对象类型，可以是 clientid，username，peerhost
     */
    private String as;
    /**
     * 指示该对象被谁添加至黑名单
     */
    private String by;
    /**
     * 添加至黑名单的时间，单位：秒
     */
    private Integer at;
    /**
     * 何时从黑名单中解除，单位：秒
     */
    private Integer until;
}

