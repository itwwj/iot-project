package com.github.iot.webhook.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jie
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WhDisconnect {
    /**
     * 终端连接断开原因
     */
    private String reason;
    /**
     * 消息目的 Client ID
     */
    private String clientid;
    /**
     * 消息目的用户名
     */
    private String username;
    /**
     * 终端的 IPAddress 和 Port
     */
    private String peername;
    /**
     * emqx 监听的 IPAddress 和 Port
     */
    private String sockname;
    /**
     * 终端连接断开时间 (s)
     */
    private Long disconnected_at;
    /**
     * 事件触发时间 (ms)
     */
    private Long timestamp;
    /**
     * 事件触发所在节点
     */
    private String node;
}
