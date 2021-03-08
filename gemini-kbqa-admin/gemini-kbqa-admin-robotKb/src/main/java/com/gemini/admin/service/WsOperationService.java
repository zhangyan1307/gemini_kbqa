package com.gemini.admin.service;

import com.gemini.admin.dto.MessageDto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

/**
 * @Author: XXY
 * @Date: 2021/3/1 21:25
 */
public interface WsOperationService {
    void sendWsMessage(MessageDto messageDto);

    void receiveMessage(String content, ChannelId channelId);

    void channelClose(ChannelId channelId);

    void channelOpen(ChannelId channelId);

    void sendRecommendQuestionsMessage(ChannelId channelId);
}
