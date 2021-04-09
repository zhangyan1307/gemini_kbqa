package com.gemini.admin.service.impl; /**
 * Copyright (c) 2021,CHENGJIINFORMATION TECHNOLOGY(SHANGHAI) O.,LTD  All Rights Reserved.
 */

import com.gemini.admin.client.CustomerKbClient;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.request.KbCustomerRecommendQuestionQueryResponse;
import com.gemini.admin.response.Response;
import com.gemini.admin.service.InputAdviceService;
import com.gemini.admin.util.EsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InputAdviceService
 * @Description 输入建议
 * @Author Gu YuLong
 * @Date 2021/3/10 9:42
 * @Version 1.0
 */
@Slf4j
@Service
public class InputAdviceServiceImpl implements InputAdviceService {

    // 定义默认查询条数
    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 1000;

    @Autowired
    private CustomerKbClient customerKbClient;

    /**
     * @author Gu YuLong
     * @description 根据关键字从ES查询问题
     * @Param: keyword
     * @date 2021/3/10 9:58
     * @return com.gemini.admin.response.Response
     */
    @Override
    public Response queryQuestionByKeyWordFromEs(String keyword) {
        List<Map<String, Object>> mapList;
        try {
            mapList = EsUtil.queryByQuestion(keyword, PAGE_NO, PAGE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("查询es报错", e);
            return Response.fail("查询es报错");
        }
        if(mapList == null || mapList.size() == 0){
            log.info("未从es查到符合关键字{}的数据", keyword);
        }
        return Response.ok(mapList);
    }

    @Override
    public List<KbCustomerRecommendQuestionQueryResponse> queryRecommendQuestions() {
        List<CustomerRecommendQuestionDto> wantAskQuestions = customerKbClient.getWantAskQuestions();
        return buildRecommendDtoToResponse(wantAskQuestions);
    }
}