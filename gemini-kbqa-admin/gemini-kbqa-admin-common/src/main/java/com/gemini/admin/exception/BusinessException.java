package com.gemini.admin.exception;

import com.gemini.admin.busiEnum.ErrorCodeEnum;
import lombok.Data;

import java.text.MessageFormat;

/**
 * @Author: XXY
 * @Date: 2020/12/13 23:39
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code = ErrorCodeEnum.SYSTEM_ERROR.getCode();
    private String message;

    public BusinessException(Integer code, String message, Object... values) {
        super(MessageFormat.format(message, values));
        this.code = code;
        this.message = MessageFormat.format(message, values);
    }

    public BusinessException(String message, Object... values) {
        super(MessageFormat.format(message, values));
        this.message = MessageFormat.format(message, values);
    }

    public BusinessException(String message, Throwable cause, Object... values) {
        super(MessageFormat.format(message, values), cause);
        this.message = MessageFormat.format(message, values);
    }
}
