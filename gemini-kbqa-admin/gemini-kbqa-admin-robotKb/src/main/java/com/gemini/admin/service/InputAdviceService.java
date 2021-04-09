package com.gemini.admin.service;

/**
 * Copyright (c) 2021,CHENGJIINFORMATION TECHNOLOGY(SHANGHAI) O.,LTD All Rights Reserved.
 */

import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.request.KbCustomerRecommendQuestionQueryResponse;
import com.gemini.admin.response.Response;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName InputAdviceService
 * @Description 输入建议
 * @Author Gu YuLong
 * @Date 2021/3/10 9:42
 * @Version 1.0
 */
public interface InputAdviceService {

    Response queryQuestionByKeyWordFromEs(String keyword);

    List<KbCustomerRecommendQuestionQueryResponse> queryRecommendQuestions();

    default List<KbCustomerRecommendQuestionQueryResponse>
        buildRecommendDtoToResponse(List<CustomerRecommendQuestionDto> customerRecommendQuestionDtos) {
        if (CollectionUtils.isEmpty(customerRecommendQuestionDtos)) {
            return Collections.emptyList();
        }
        List<KbCustomerRecommendQuestionQueryResponse> list = new ArrayList<>();
        customerRecommendQuestionDtos.forEach(customerRecommendQuestionDto -> {
            KbCustomerRecommendQuestionQueryResponse response = new KbCustomerRecommendQuestionQueryResponse();
            response.setQuestion(customerRecommendQuestionDto.getQuestion());
            response.setQuestionId(customerRecommendQuestionDto.getId());
            response.setAnswer(customerRecommendQuestionDto.getAnswer());
            response.setMessageSender("小勇智能机器人");
            list.add(response);
        });
        return list;
    }
}