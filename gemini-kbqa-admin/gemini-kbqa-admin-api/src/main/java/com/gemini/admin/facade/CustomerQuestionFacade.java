package com.gemini.admin.facade;

import com.gemini.admin.common.ApiResult;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/3/3 23:39
 * 问答dubbo接口
 */
public interface CustomerQuestionFacade {
    /**
     * 猜你想问列表:用户最常点击的五个问题
     * @return
     */
    ApiResult<List<CustomerRecommendQuestionDto>> supposeWantToAskQuestions();
}
