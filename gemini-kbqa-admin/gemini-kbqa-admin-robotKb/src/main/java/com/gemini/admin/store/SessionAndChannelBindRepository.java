package com.gemini.admin.store;

import com.gemini.admin.dto.WsSessionDto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: XXY
 * @Date: 2021/3/1 23:25
 * 用于存储用户session与channel
 */
@Component
public class SessionAndChannelBindRepository {

    private Map<WsSessionDto, Channel> sessionDtoChannelMap = new ConcurrentHashMap<>();

    private Map<String, WsSessionDto> sessionDtoMap = new ConcurrentHashMap<>();

    public Channel getChannelBySession(WsSessionDto sessionDto){
        return sessionDtoChannelMap.get(sessionDto);
    }

    public void saveChannel(WsSessionDto sessionDto,Channel channel){
        sessionDtoChannelMap.put(sessionDto, channel);
        sessionDtoMap.put(sessionDto.getSession(), sessionDto);
    }

    public WsSessionDto generateSession(ChannelId channelId){
        WsSessionDto sessionDto = new WsSessionDto();
        sessionDto.setSession(channelId.asLongText());
        return sessionDto;
    }

    public void removeSessionAndChannel(String session){
        WsSessionDto sessionDto = sessionDtoMap.get(session);
        sessionDtoChannelMap.remove(sessionDto);
        sessionDtoMap.remove(sessionDto.getSession());
    }

    public WsSessionDto getSessionBySession(String session){
        return sessionDtoMap.get(session);
    }



}
