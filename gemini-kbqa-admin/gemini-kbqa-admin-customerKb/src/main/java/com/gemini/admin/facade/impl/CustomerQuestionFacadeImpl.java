package com.gemini.admin.facade.impl;

import com.gemini.admin.common.ApiResult;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.facade.CustomerQuestionFacade;
import com.gemini.admin.service.CustomerKbQuestionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/3/4 7:44
 */
@Service(version = "1.0.0")
public class CustomerQuestionFacadeImpl implements CustomerQuestionFacade {

    @Autowired
    private CustomerKbQuestionService customerKbQuestionService;

    @Override
    public ApiResult<List<CustomerRecommendQuestionDto>> supposeWantToAskQuestions() {
        return ApiResult.success(customerKbQuestionService.queryRecommendQuestions());
    }
}
