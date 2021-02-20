package com.gemini.admin.ws;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: XXY
 * @Date: 2021/2/19 19:54
 */
public class ChatWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger log = LoggerFactory.getLogger(ChatWebSocketHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端建立连接，通道开启");
        //添加到通道组
        ChatChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端连接断开，通道关闭");
        ChatChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接时FullHttpRequest，处理参数
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }
}
