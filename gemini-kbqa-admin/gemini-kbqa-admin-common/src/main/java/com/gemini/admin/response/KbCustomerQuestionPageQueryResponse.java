package com.gemini.admin.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/2/12 13:11
 */
@Data
public class KbCustomerQuestionPageQueryResponse {
    private Long id;

    private String question;

    private String answer;

    private Integer categoryId;

    private List<KbCustomerSimilarityQuestionQueryResponse> similarityQuestionQueryResponses;

    private List<KbCustomerRelationQuestionQueryResponse> relationQuestionQueryResponses;

}
