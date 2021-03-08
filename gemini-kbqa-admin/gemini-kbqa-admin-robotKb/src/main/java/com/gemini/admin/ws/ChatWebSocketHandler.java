package com.gemini.admin.ws;

import com.alibaba.fastjson.JSON;
import com.gemini.admin.dto.MessageDto;
import com.gemini.admin.dto.WsSessionDto;
import com.gemini.admin.service.WsOperationService;
import com.gemini.admin.store.SessionAndChannelBindRepository;
import com.gemini.admin.util.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2021/2/19 19:54
 */
public class ChatWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger log = LoggerFactory.getLogger(ChatWebSocketHandler.class);

    private static SessionAndChannelBindRepository sessionAndChannelBindRepository;

    private static WsOperationService wsOperationService;

    static {
        sessionAndChannelBindRepository = SpringUtil.getBean(SessionAndChannelBindRepository.class);
        wsOperationService = SpringUtil.getBean(WsOperationService.class);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        WsSessionDto session = getSession(ctx.channel().id());
        sessionAndChannelBindRepository.saveChannel(session, ctx.channel());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        sessionAndChannelBindRepository.removeSessionAndChannel(ctx.channel().id().asLongText());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            wsOperationService.receiveMessage(((TextWebSocketFrame)msg).text(), ctx.channel().id());
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {

    }

    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }

    private WsSessionDto getSession(ChannelId channelId) {
        return sessionAndChannelBindRepository.generateSession(channelId);
    }

}
