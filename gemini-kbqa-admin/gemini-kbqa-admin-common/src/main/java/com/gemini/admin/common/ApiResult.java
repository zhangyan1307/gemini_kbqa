package com.gemini.admin.common;

import com.gemini.admin.busiEnum.ErrorCodeEnum;
import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2020/12/15 23:22
 */
@Data
public class ApiResult<T> {
    private T data;
    private Integer code = ErrorCodeEnum.SUCCESS.getCode();
    private String message;
    public ApiResult(T data, Integer code, String message){
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public ApiResult(T data, String message){
        this.data = data;
        this.message = message;
    }
    public ApiResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public ApiResult(T data){
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.data = data;
    }
    public static <T>  ApiResult<T> success(T data){
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> fail(Integer code, String message){
        return new ApiResult<>(code, message);
    }
}
