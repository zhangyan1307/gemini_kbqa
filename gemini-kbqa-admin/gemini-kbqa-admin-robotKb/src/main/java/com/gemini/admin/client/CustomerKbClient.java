package com.gemini.admin.client;

import com.gemini.admin.common.ApiResult;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.exception.BusinessException;
import com.gemini.admin.facade.CustomerQuestionFacade;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/12/14 22:41
 */
@Component
public class CustomerKbClient {
    @Reference(timeout = 3000, version = "1.0.0", check = false, retries = 3)
    private CustomerQuestionFacade customerQuestionFacade;

    public List<CustomerRecommendQuestionDto> getWantAskQuestions() {
        ApiResult<List<CustomerRecommendQuestionDto>> result = customerQuestionFacade.supposeWantToAskQuestions();
        if(!result.isSuccess()){
            throw new BusinessException("获取猜你想问失败{0}", result.getMessage());
        }
        return result.getData();
    }

}
