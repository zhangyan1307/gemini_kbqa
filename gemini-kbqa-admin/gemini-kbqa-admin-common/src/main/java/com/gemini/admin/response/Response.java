package com.gemini.admin.response;

import com.gemini.admin.busiEnum.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2020/12/1 23:52
 */
@Data
@AllArgsConstructor
public class Response<T> {
    private T data;
    private Integer code;
    private String message;

    private Response(T data){
        this.data = data;
        this.code = ErrorCodeEnum.SUCCESS.getCode();
    }

    private Response(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    private Response(){
        this.code = ErrorCodeEnum.SUCCESS.getCode();
    }

    public static <T> Response<T> ok(T data){
        return new Response<>(data);
    }
    public static <T> Response<T> ok(){
        return new Response<>();
    }
    public static <T> Response<T> fail(){
        return new Response<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getDesc());
    }
    public static <T> Response<T> fail(String desc){
        return new Response<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(), desc);
    }
}
