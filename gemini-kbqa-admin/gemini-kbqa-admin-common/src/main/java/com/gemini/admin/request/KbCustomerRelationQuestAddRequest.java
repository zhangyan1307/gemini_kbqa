package com.gemini.admin.request;

import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2021/2/12 13:43
 */
@Data
public class KbCustomerRelationQuestAddRequest {
    private Long questionId;

    private String relationQuestion;

    private String relationAnswer;
}
