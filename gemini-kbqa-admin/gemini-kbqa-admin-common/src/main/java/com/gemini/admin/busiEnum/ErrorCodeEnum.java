package com.gemini.admin.busiEnum;

import io.swagger.models.auth.In;
import lombok.Getter;

/**
 * @Author: XXY
 * @Date: 2020/12/13 23:45
 */
public enum  ErrorCodeEnum {
    SYSTEM_ERROR(1008, "系统异常"),
    BUSINESS_ERROR(1005, "业务异常");

    @Getter
    private Integer code;
    @Getter
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
