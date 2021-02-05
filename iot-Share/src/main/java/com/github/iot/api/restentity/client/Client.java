package com.github.iot.api.restentity.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    /**
     *指示客户端使用的配置组
     */
    private String zone;
    /**
     *接收的 TCP 报文数量
     */
    private int recv_cnt;
    /**
     *消息队列最大长度
     */
    private int max_mqueue;
    /**
     *客户端所连接的节点名称
     */
    private String node;
    /**
     *客户端连接时使用的用户名
     */
    private String username;
    /**
     *消息队列当前长度
     */
    private int mqueue_len;
    /**
     *飞行队列最大长度
     */
    private int max_inflight;
    /**
     *指示客户端是否通过桥接方式连接
     */
    private boolean is_bridge;
    /**
     *消息队列因超出长度而丢弃的消息数量
     */
    private int mqueue_dropped;
    /**
     *飞行队列当前长度
     */
    private int inflight;
    /**
     *进程堆栈大小，单位：字节
     */
    private int heap_size;
    /**
     *此客户端允许建立的最大订阅数量
     */
    private int max_subscriptions;
    /**
     *客户端协议名称
     */
    private String proto_name;
    /**
     *会话创建时间，格式为 "YYYY-MM-DD HH:mm:ss"
     */
    private String created_at;
    /**
     *客户端使用的协议版本
     */
    private int proto_ver;
    /**
     *Erlang reduction
     */
    private int reductions;
    /**
     *发送的 PUBLISH 报文数量
     */
    private int send_msg;
    /**
     *客户端的 IP 地址
     */
    private String ip_address;
    /**
     *发送的 TCP 报文数量
     */
    private int send_cnt;
    /**
     *进程邮箱大小
     */
    private int mailbox_len;
    /**
     *未确认的 PUBREC 报文数量
     */
    private int awaiting_rel;
    /**
     *保持连接时间，单位：秒
     */
    private int keepalive;
    /**
     *接收的 PUBLISH 报文数量
     */
    private int recv_msg;
    /**
     *发送的 MQTT 报文数量
     */
    private int send_pkt;
    /**
     *EMQ X Broker（下同）接收的字节数量
     */
    private int recv_oct;
    /**
     *客户端标识符
     */
    private String clientid;
    /**
     *指示客户端是否使用了全新的会话
     */
    private boolean clean_start;
    /**
     *会话过期间隔，单位：秒
     */
    private int expiry_interval;
    /**
     *客户端是否处于连接状态
     */
    private boolean connected;
    /**
     *客户端的端口
     */
    private int port;
    /**
     *发送的字节数量
     */
    private int send_oct;
    /**
     *接收的 MQTT 报文数量
     */
    private int recv_pkt;
    /**
     *客户端连接时间，格式为 "YYYY-MM-DD HH:mm:ss"
     */
    private String connected_at;
    /**
     *允许存在未确认的 PUBREC 报文的最大数量
     */
    private int max_awaiting_rel;
    /**
     *此客户端已建立的订阅数量
     */
    private int subscriptions_cnt;
}
