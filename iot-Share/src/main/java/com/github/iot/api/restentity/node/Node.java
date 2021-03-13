package com.github.iot.api.restentity.node;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
public class Node {
    /**
     * EMQ X 版本
     */
    private String version;
    /**
     * EMQ X 运行时间
     */
    private String uptime;
    /**
     * 已占用的进程数量
     */
    private int process_used;
    /**
     * 可用的进程数量
     */
    private int process_available;
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
     * VM 已占用的内存大小
     */
    private String memory_used;
    /**
     * VM 已分配的系统内存
     */
    private String memory_total;
    /**
     * 操作系统的最大文件描述符限制
     */
    private int max_fds;
    /**
     * 15 分钟内的 CPU 平均负载
     */
    private String load15;
    /**
     * 5 分钟内的 CPU 平均负载
     */
    private String load5;
    /**
     * 1 分钟内的 CPU 平均负载
     */
    private String load1;
    /**
     * 当前接入此节点的客户端数量
     */
    private int connections;
}
