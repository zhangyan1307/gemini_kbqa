package com.gemini.admin.response;

import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2020/12/1 23:52
 */
@Data
public class Response<T> {
    private T data;
    private String code;
    private String message;

    public Response ok(T data){
        this.data = data;
        return this;
    }
}
