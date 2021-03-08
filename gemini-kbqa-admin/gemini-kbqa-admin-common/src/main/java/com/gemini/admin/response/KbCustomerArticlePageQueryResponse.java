package com.gemini.admin.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/1 23:26
 */
@Data
public class KbCustomerArticlePageQueryResponse {

    private Long id;

    /**
     * 文档名称
     */
    private String articleName;

    /**
     * 文档标题
     */
    private String articleTitle;

    /**
     * 文档分类
     */
    private String articleCategory;

    /**
     * 文档描述
     */
    private String articleDesc;

    /**
     * 文档内容
     */
    private String articleContent;

    /**
     * 文档类型
     */
    private String articleType;
}
