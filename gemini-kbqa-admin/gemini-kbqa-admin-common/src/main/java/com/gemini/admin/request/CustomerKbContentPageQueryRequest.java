package com.gemini.admin.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/1/28 23:56
 * 客服内部知识库列表查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerKbContentPageQueryRequest extends CommonPageRequest{
    /**
     * 分类id
     */
    private Integer categoryId;

}
