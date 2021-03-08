package com.gemini.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.dao.CustomerKbQuestionDaoImpl;
import com.gemini.admin.dto.CustomerRecommendQuestionDto;
import com.gemini.admin.entity.KbQuestion;
import com.gemini.admin.entity.KbSimilarityQuestion;
import com.gemini.admin.exception.BusinessException;
import com.gemini.admin.request.KbCustomerQuestionAddRequest;
import com.gemini.admin.request.KbCustomerQuestionPageQueryRequest;
import com.gemini.admin.request.KbCustomerSimilarityQuestionAddRequest;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.KbCustomerQuestionPageQueryResponse;
import com.gemini.admin.service.CustomerKbQuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/2/12 14:12
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerKbQuestionServiceImpl implements CustomerKbQuestionService {

    private final CustomerKbQuestionDaoImpl customerKbQuestionDao;

    @Override
    public CommonPageResponse<KbCustomerQuestionPageQueryResponse>
        queryCustomerKbQuestionByPage(KbCustomerQuestionPageQueryRequest request) {
        IPage<KbQuestion> questionIPage = customerKbQuestionDao
            .selectKbQuestionListPageByCategoryId(request.getCategoryId(), request.getPageSize(), request.getPageNum());
        return buildQuestionDaoToResponse(questionIPage, customerKbQuestionDao);
    }

    @Transactional
    @Override
    public void deleteQuestion(Long questionId) {
        try {
            customerKbQuestionDao.deleteRelationQuestion(questionId);
            customerKbQuestionDao.deleteSimilarityQuestion(questionId);
            customerKbQuestionDao.deleteKbQuestion(questionId);
        } catch (Exception e) {
            log.error("删除问答数据失败：{}", questionId);
            throw new BusinessException("删除问答数据失败:{0}", e, questionId);
        }

    }

    @Transactional
    @Override
    public void saveQuestion(KbCustomerQuestionAddRequest request) {
        try {
            KbQuestion kbQuestion = buildKbQuestionAddRequestToKbQuestion(request);
            Long questionId = customerKbQuestionDao.saveKbQuestion(kbQuestion);
            List<KbCustomerSimilarityQuestionAddRequest> similarityQuestionAddRequests =
                buildStringQuestionToRequest(request.getSimilarityQuestions(), request.getAnswer());
            List<KbSimilarityQuestion> kbSimilarityQuestions =
                buildSimilarityQuestionRequestsToDao(similarityQuestionAddRequests, questionId);
            kbSimilarityQuestions.forEach(customerKbQuestionDao::saveSimilarityQuestion);
        } catch (Exception e) {
            log.error("添加问答知识失败：{}", request, e);
            throw new BusinessException("添加问答知识失败：{0}", e, request);
        }

    }

    @Override
    public void deleteSimilarityQuestion(Long id) {
        try {
            customerKbQuestionDao.deleteSimilarityQuestion(id);
        } catch (Exception e) {
            log.error("删除相似问知识失败：{}", id, e);
            throw new BusinessException("删除相似问知识失败:{0}", e, id);
        }
    }

    @Override
    public void deleteRelationQuestion(Long id) {
        try {
            customerKbQuestionDao.deleteRelationQuestion(id);
        } catch (Exception e) {
            log.error("删除关联问知识失败：{}", id, e);
            throw new BusinessException("删除关联问知识失败:{0}", e, id);
        }
    }

    @Override
    public List<CustomerRecommendQuestionDto> queryRecommendQuestions() {
        return buildKbQuestionsToCustomerQuestionsDto(customerKbQuestionDao.selectRecommendQuestions());
    }
}
