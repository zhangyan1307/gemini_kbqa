package com.gemini.admin.response;

import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2021/2/12 13:39
 */
@Data
public class KbCustomerRelationQuestionQueryResponse {
    private Long id;

    private Long questionId;

    private String relationQuestion;

    private String relationAnswer;

}
