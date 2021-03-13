package com.github.emqx;

import io.emqx.exproto.AbstractExProtoHandler;
import io.emqx.exproto.Connection;
import io.emqx.exproto.ConnectionInfo;
import io.emqx.exproto.Message;

/**
 * @author jie
 */
public class JavaSdk extends AbstractExProtoHandler {
    /**
     * 终端建立连接
     * @param connection
     * @param connectionInfo
     */
    @Override
    public void onConnectionEstablished(Connection connection, ConnectionInfo connectionInfo) {

    }

    /**
     * 终端发送消息至EMQ X Borker
     * @param connection
     * @param bytes
     */
    @Override
    public void onConnectionReceived(Connection connection, byte[] bytes) {

    }

    /**
     * 终端断开连接
     * @param connection
     * @param s
     */
    @Override
    public void onConnectionTerminated(Connection connection, String s) {

    }

    /**
     * EMQ X Broker订阅消息
     * @param connection
     * @param messages
     */
    @Override
    public void onConnectionDeliver(Connection connection, Message[] messages) {

    }
}
