package com.gemini.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.gemini.admin.client.CustomerKbClient;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.dto.MessageDto;
import com.gemini.admin.enums.MessageTypeEnum;
import com.gemini.admin.service.WsOperationService;
import com.gemini.admin.store.SessionAndChannelBindRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: XXY
 * @Date: 2021/3/1 21:52
 */
@Service
@AllArgsConstructor
public class WsOperationServiceImpl implements WsOperationService {

    private final SessionAndChannelBindRepository sessionAndChannelBindRepository;

    private final CustomerKbClient customerKbClient;

    @Override
    public void sendWsMessage(MessageDto messageDto) {

    }

    @Override
    public void receiveMessage(String content, ChannelId channelId) {
        if(content.contains("openFlag")){
            sendRecommendQuestionsMessage(channelId);
        }

    }

    @Override
    public void channelClose(ChannelId channelId) {

    }

    @Override
    public void channelOpen(ChannelId channelId) {

    }

    @Override
    public void sendRecommendQuestionsMessage(ChannelId channelId) {
        Channel channel = sessionAndChannelBindRepository
            .getChannelBySession(sessionAndChannelBindRepository.generateSession(channelId));
        List<CustomerRecommendQuestionDto> wantAskQuestions = customerKbClient.getWantAskQuestions();
        MessageDto messageDto = new MessageDto();
        messageDto.buildMessage(JSON.toJSONString(wantAskQuestions), MessageTypeEnum.ROBOT_CARD,
            UUID.randomUUID().toString(), "机器人小勇", "小勇id");
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(messageDto)));
    }
}
