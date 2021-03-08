package com.gemini.admin.enums;

import lombok.Getter;

/**
 * @Author: XXY
 * @Date: 2021/3/3 22:12
 */
public enum  MessageTypeEnum {
    TEXT(1, "[文本消息]"),
    ROBOT_CARD(2, "[机器人推荐问题卡片消息]"),
    SYSTEM_MESSAGE(3, "[系统消息]"),
    VIDEO_MESSAGE(4, "[视频]"),
    IMAGE_MESSAGE(5, "[图片]");
    @Getter
    private Integer messageType;
    @Getter
    private String desc;

    MessageTypeEnum(Integer messageType, String desc) {
        this.messageType = messageType;
        this.desc = desc;
    }
}
