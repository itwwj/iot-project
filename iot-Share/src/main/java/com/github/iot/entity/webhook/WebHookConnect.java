package com.github.iot.entity.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WebHookConnect {
    /**
     * 消息目的 Client ID
     */
    private String clientid;	
    /**
     * 消息目的用户名
     */
    private String username	;
    /**
     * 主题挂载点(主题前缀)
     */
    private String mountpoint;	
    /**
     * 终端的 IPAddress 和 Port
     */
    private String peername;	
    /**
     * emqx 监听的 IPAddress 和 Port
     */
    private String sockname;	
    /**
     * 协议名字
     */
    private String proto_name;	
    /**
     * 协议版本
     */
    private String proto_ver;	
    /**
     * MQTT 保活间隔
     */
    private Long keepalive;
    /**
     * MQTT clean_start
     */
    private Boolean clean_start;
    /**
     * MQTT Session 过期时间
     */
    private Long expiry_interval;
    /**
     * 是否为 MQTT bridge 连接
     */
    private Boolean is_bridge;
    /**
     * 终端连接完成时间 (s)
     */
    private Long connected_at;
    /**
     * 事件触发时间 (ms)
     */
    private Long timestamp;
    /**
     * 事件触发所在节点
     */
    private String node;	
}
