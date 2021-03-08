package com.gemini.admin.dto;

import com.gemini.admin.enums.MessageTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2021/3/1 21:27
 */
@Data
public class MessageDto implements Serializable {

    private String message;

    /**
     * 消息类型
     */
    private Integer messageType;

    private String messageId;

    private String messageSender;

    private String messageSenderId;

    /**
     * 扩展字段
     */
    private Map<String, String> extendData;

    public void buildMessage(String message, MessageTypeEnum messageTypeEnum, String messageId, String messageSender,
        String messageSenderId) {
        this.message = message;
        this.messageType = messageTypeEnum.getMessageType();
        this.messageId = messageId;
        this.messageSender = messageSender;
        this.messageSenderId = messageSenderId;
    }
}
