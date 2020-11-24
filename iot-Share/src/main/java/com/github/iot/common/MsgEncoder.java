package com.github.iot.common;

/**
 * @author jie
 */
public interface MsgEncoder<T> {
    /**
     * 数据库消息编码为string
     * @param t
     * @return
     */
    String encoder(T t);
}
