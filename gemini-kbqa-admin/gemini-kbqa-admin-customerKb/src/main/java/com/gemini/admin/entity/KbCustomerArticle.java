package com.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/1 22:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_customer_article")
public class KbCustomerArticle extends BaseEntity{

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
     * 文档分类id
     */
    private Integer articleCategoryId;

    /**
     * 文档描述
     */
    private String articleDesc;

    /**
     * 文档地址
     */
    private String articleContent;

    /**
     * 文档类型
     */
    private String articleType;

}
