package com.gemini.admin.request;

import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2021/1/28 23:44
 * 删除客服内部知识库request
 */
@Data
public class CustomerKbContentDeleteRequest {

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 文件id
     */
    private String fileId;
}
