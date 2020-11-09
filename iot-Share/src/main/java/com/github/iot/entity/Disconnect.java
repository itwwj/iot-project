package com.github.iot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>设备下线
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Disconnect {
    /**
     * 客户端id
     */
    private String clientid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 终端连接断开原因
     */
    private String reason;
    /**
     * 事件触发时间 (ms)
     */
    private long ts;
}
