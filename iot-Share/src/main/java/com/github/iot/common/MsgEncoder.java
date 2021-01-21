package com.github.iot.common;

/**
 * @author jie
 */
public interface MsgEncoder<T> {
    /**
     * 消息编码为字节数组
     * @param t
     * @return
     */
    byte[] encoder(T t);
}
