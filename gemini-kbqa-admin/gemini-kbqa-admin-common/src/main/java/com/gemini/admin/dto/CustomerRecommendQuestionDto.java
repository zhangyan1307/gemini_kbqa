package com.gemini.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2021/3/4 7:36
 */
@Data
public class CustomerQuestionDto implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 分类id
     */
    private Integer categoryId;

    private Integer recommendFlag;
}
