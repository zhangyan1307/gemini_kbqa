package com.gemini.admin.exception;

import com.gemini.admin.busiEnum.ErrorCodeEnum;
import lombok.Data;

import java.text.MessageFormat;

/**
 * @Author: XXY
 * @Date: 2020/12/13 23:39
 */
@Data
public class BusinessException extends RuntimeException{

    private Integer code = ErrorCodeEnum.SYSTEM_ERROR.getCode();
    private String message;

    public BusinessException(Integer code, String message, Object... values){
        this.code = code;
        String format = MessageFormat.format(message, values);
        this.message = format;
    }

    public BusinessException(String message, Object... values){
        String format = MessageFormat.format(message, values);
        this.message = format;
    }
}
