package com.github.iot.api.restentity.broker;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
public class Brokers {
    /**
     * EMQ X 版本
     */
    private String version;
    /**
     * EMQ X 运行时间，格式为 "H hours, m minutes, s seconds"
     */
    private String uptime;
    /**
     * 软件描述
     */
    private String sysdescr;
    /**
     * EMQ X 使用的 Erlang/OTP 版本
     */
    private String otp_release;
    /**
     * 节点状态
     */
    private String node_status;
    /**
     * 节点名称
     */
    private String node;
    /**
     * 当前时间，格式为 "YYYY-MM-DD HH:mm:ss"
     */
    private String datetime;
}
