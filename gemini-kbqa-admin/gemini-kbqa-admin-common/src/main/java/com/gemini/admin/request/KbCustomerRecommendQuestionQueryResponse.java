package com.gemini.admin.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2021/3/21 23:38
 */
@Data
public class KbCustomerRecommendQuestionQueryResponse implements Serializable {
    /**
     * 问题id
     */
    private Long questionId;

    /**
     * 问题
     */
    private String question;

    private String answer;

    private String messageSender;
}
