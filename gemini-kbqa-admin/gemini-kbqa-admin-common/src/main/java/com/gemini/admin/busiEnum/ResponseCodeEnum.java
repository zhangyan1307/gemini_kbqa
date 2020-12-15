package com.gemini.admin.busiEnum;

import lombok.Getter;

/**
 * @Author: XXY
 * @Date: 2020/12/13 23:36
 */
public enum ResponseCodeEnum {
    SUCCESS(1001, "成功"),
    FAIL(1005, "失败"),
    WARN(1002, "提示");

    ResponseCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    @Getter
    private Integer code;
    @Getter
    private String desc;
}
