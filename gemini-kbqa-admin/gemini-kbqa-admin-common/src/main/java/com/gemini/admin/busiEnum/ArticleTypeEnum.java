package com.gemini.admin.busiEnum;

import lombok.Getter;

/**
 * @Author: XXY
 * @Date: 2021/1/28 22:47
 */
public enum ArticleTypeEnum {
    FILE("FILE", "文件"),
    TEXT("TEXT", "文本");

    @Getter
    private final String type;
    @Getter
    private final String desc;

    ArticleTypeEnum(String desc, String type) {
        this.desc = desc;
        this.type = type;
    }
}
