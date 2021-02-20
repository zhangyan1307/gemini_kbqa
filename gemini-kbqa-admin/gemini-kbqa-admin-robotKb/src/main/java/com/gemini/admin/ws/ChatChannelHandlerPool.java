package com.gemini.admin.ws;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: XXY
 * @Date: 2021/2/19 19:57
 * 管理所有webSocket连接
 */
public class ChatChannelHandlerPool {
    public ChatChannelHandlerPool(){}

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
