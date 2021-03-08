package com.gemini.admin.request;

import com.gemini.admin.response.KbCustomerRelationQuestionQueryResponse;
import com.gemini.admin.response.KbCustomerSimilarityQuestionQueryResponse;
import lombok.Data;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/2/12 13:39
 */
@Data
public class KbCustomerQuestionAddRequest {
    private Long id;

    private String question;

    private String answer;

    private Integer categoryId;

    private Integer recommendFlag;

    private List<KbCustomerSimilarityQuestionAddRequest> similarityQuestionAddRequests;

    private List<KbCustomerRelationQuestAddRequest> relationQuestAddRequests;

    private List<String> relationQuestions;

    private List<String> similarityQuestions;
}
