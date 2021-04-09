package com.gemini.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.dao.CustomerKbQuestionDaoImpl;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.entity.KbQuestion;
import com.gemini.admin.entity.KbRelationQuestion;
import com.gemini.admin.entity.KbSimilarityQuestion;
import com.gemini.admin.request.*;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.KbCustomerQuestionPageQueryResponse;
import com.gemini.admin.response.KbCustomerRelationQuestionQueryResponse;
import com.gemini.admin.response.KbCustomerSimilarityQuestionQueryResponse;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/2/9 16:19 问答知识库操作服务
 */
public interface CustomerKbQuestionService {
    /**
     * 分页查询问答知识库列表
     *
     * @param request
     * @return
     */
    CommonPageResponse<KbCustomerQuestionPageQueryResponse>
        queryCustomerKbQuestionByPage(KbCustomerQuestionPageQueryRequest request);

    /**
     * 删除问答对
     *
     * @param questionId
     */
    void deleteQuestion(Long questionId);

    /**
     * 新增问答对
     *
     * @param request
     */
    void saveQuestion(KbCustomerQuestionAddRequest request);

    /**
     * 删除相似问答对
     *
     * @param id
     */
    void deleteSimilarityQuestion(Long id);

    /**
     * 删除关联问答对
     *
     * @param id
     */
    void deleteRelationQuestion(Long id);

    /**
     * 获取推荐问题
     * 
     * @return
     */
    List<CustomerRecommendQuestionDto> queryRecommendQuestions();

    void setRecommendFlag(KbSetRecommendRequest setRecommendRequest);

    default CommonPageResponse<KbCustomerQuestionPageQueryResponse> buildQuestionDaoToResponse(IPage<KbQuestion> page,
        CustomerKbQuestionDaoImpl customerKbQuestionDao) {
        CommonPageResponse<KbCustomerQuestionPageQueryResponse> commonPageResponse = new CommonPageResponse<>();
        commonPageResponse.setPageSize((int)page.getSize());
        commonPageResponse.setPageNum((int)page.getCurrent());
        commonPageResponse.setTotal(page.getTotal().intValue());
        List<KbCustomerQuestionPageQueryResponse> responses = new ArrayList<>();
        if (page.getTotal() == 0) {
            commonPageResponse.setData(responses);
            return commonPageResponse;
        }

        page.getRecords().forEach(kbQuestion -> {
            KbCustomerQuestionPageQueryResponse response = new KbCustomerQuestionPageQueryResponse();
            response.setAnswer(kbQuestion.getAnswer());
            response.setCategoryId(kbQuestion.getCategoryId());
            response.setQuestion(kbQuestion.getQuestion());
            response.setId(kbQuestion.getId());
            response.setRecommendFlag(kbQuestion.getRecommendFlag());
            response.setRelationQuestionQueryResponses(buildRelationQuestionQueryResponses(
                customerKbQuestionDao.selectRelationQuestionsByQuestionId(kbQuestion.getId())));
            response.setSimilarityQuestionQueryResponses(buildSimilarityQueryResponses(
                customerKbQuestionDao.selectSimilarityQuestionsByQuestionId(kbQuestion.getId())));
            responses.add(response);
        });
        commonPageResponse.setData(responses);
        return commonPageResponse;
    }

    default List<KbCustomerSimilarityQuestionQueryResponse>
        buildSimilarityQueryResponses(List<KbSimilarityQuestion> list) {
        List<KbCustomerSimilarityQuestionQueryResponse> responses = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return responses;
        }
        list.forEach(similarityQuestion -> {
            KbCustomerSimilarityQuestionQueryResponse response = new KbCustomerSimilarityQuestionQueryResponse();
            response.setId(similarityQuestion.getId());
            response.setQuestionId(similarityQuestion.getQuestionId());
            response.setSimilarityAnswer(similarityQuestion.getSimilarityAnswer());
            response.setSimilarityQuestion(similarityQuestion.getSimilarityQuestion());
            responses.add(response);
        });
        return responses;
    }

    default List<KbCustomerRelationQuestionQueryResponse>
        buildRelationQuestionQueryResponses(List<KbRelationQuestion> list) {
        List<KbCustomerRelationQuestionQueryResponse> responses = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return responses;
        }
        list.forEach(relationQuestion -> {
            KbCustomerRelationQuestionQueryResponse response = new KbCustomerRelationQuestionQueryResponse();
            response.setId(relationQuestion.getId());
            response.setQuestionId(relationQuestion.getQuestionId());
            response.setRelationAnswer(relationQuestion.getRelationAnswer());
            response.setRelationQuestion(relationQuestion.getRelationQuestion());
            responses.add(response);
        });
        return responses;
    }

    default List<KbRelationQuestion> buildRelationQuestionRequestsToDao(
        List<KbCustomerRelationQuestAddRequest> relationQuestAddRequests, Long questionId) {
        List<KbRelationQuestion> kbRelationQuestions = new ArrayList<>();
        if (CollectionUtils.isEmpty(relationQuestAddRequests)) {
            return kbRelationQuestions;
        }
        relationQuestAddRequests.forEach(addRequest -> {
            KbRelationQuestion question = new KbRelationQuestion();
            question.setQuestionId(questionId);
            question.setRelationAnswer(addRequest.getRelationAnswer());
            question.setRelationQuestion(addRequest.getRelationQuestion());
            kbRelationQuestions.add(question);
        });
        return kbRelationQuestions;
    }

    default List<KbSimilarityQuestion> buildSimilarityQuestionRequestsToDao(
        List<KbCustomerSimilarityQuestionAddRequest> similarityQuestionAddRequests, Long questionId) {
        List<KbSimilarityQuestion> kbSimilarityQuestions = new ArrayList<>();
        if (CollectionUtils.isEmpty(similarityQuestionAddRequests)) {
            return kbSimilarityQuestions;
        }
        similarityQuestionAddRequests.forEach(addRequest -> {
            KbSimilarityQuestion question = new KbSimilarityQuestion();
            question.setQuestionId(questionId);
            question.setSimilarityAnswer(addRequest.getSimilarityAnswer());
            question.setSimilarityQuestion(addRequest.getSimilarityQuestion());
            kbSimilarityQuestions.add(question);
        });
        return kbSimilarityQuestions;
    }

    default List<KbCustomerSimilarityQuestionAddRequest> buildStringQuestionToRequest(List<String> similarityQuestions,
        String answer) {
        List<KbCustomerSimilarityQuestionAddRequest> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(similarityQuestions)) {
            return list;
        }
        similarityQuestions.forEach(similarityQuestion -> {
            KbCustomerSimilarityQuestionAddRequest request = new KbCustomerSimilarityQuestionAddRequest();
            request.setSimilarityQuestion(similarityQuestion);
            request.setSimilarityAnswer(answer);
            list.add(request);
        });
        return list;
    }

    default KbQuestion
        buildKbQuestionAddRequestToKbQuestion(KbCustomerQuestionAddRequest kbCustomerQuestionAddRequest) {
        KbQuestion kbQuestion = new KbQuestion();
        kbQuestion.setAnswer(kbCustomerQuestionAddRequest.getAnswer());
        kbQuestion.setQuestion(kbCustomerQuestionAddRequest.getQuestion());
        kbQuestion.setCategoryId(kbCustomerQuestionAddRequest.getCategoryId());
        kbQuestion.setRecommendFlag(kbCustomerQuestionAddRequest.getRecommendFlag());
        return kbQuestion;
    }

    default List<CustomerRecommendQuestionDto> buildKbQuestionsToCustomerQuestionsDto(List<KbQuestion> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<CustomerRecommendQuestionDto> customerRecommendQuestionDtos = new ArrayList<>();
        list.forEach(question -> {
            customerRecommendQuestionDtos.add(buildKbQuestionToCustomerDto(question));
        });
        return customerRecommendQuestionDtos;
    }

    default CustomerRecommendQuestionDto buildKbQuestionToCustomerDto(KbQuestion question) {
        CustomerRecommendQuestionDto customerRecommendQuestionDto = new CustomerRecommendQuestionDto();
        customerRecommendQuestionDto.setAnswer(question.getAnswer());
        customerRecommendQuestionDto.setRecommendFlag(question.getRecommendFlag());
        customerRecommendQuestionDto.setId(question.getId());
        customerRecommendQuestionDto.setCategoryId(question.getCategoryId());
        customerRecommendQuestionDto.setQuestion(question.getQuestion());
        return customerRecommendQuestionDto;
    }

}
