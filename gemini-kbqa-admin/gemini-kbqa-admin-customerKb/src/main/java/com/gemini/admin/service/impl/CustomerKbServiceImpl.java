package com.gemini.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.dao.CustomerKbDaoImpl;
import com.gemini.admin.entity.KbCategory;
import com.gemini.admin.entity.KbCustomerArticle;
import com.gemini.admin.exception.BusinessException;
import com.gemini.admin.request.CustomerKbCategoryAddRequest;
import com.gemini.admin.request.CustomerKbContentAddRequest;
import com.gemini.admin.request.CustomerKbContentDeleteRequest;
import com.gemini.admin.request.CustomerKbContentPageQueryRequest;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.CustomerCategoryResponse;
import com.gemini.admin.response.KbCustomerArticlePageQueryResponse;
import com.gemini.admin.service.CustomerKbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/12/14 23:28
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerKbServiceImpl implements CustomerKbService {

    private final CustomerKbDaoImpl customerKbDao;

    @Override
    public List<CustomerCategoryResponse> getCustomerKbCategoryList(Integer categoryId) {
        List<KbCategory> kbCategories = customerKbDao.selectKbCategoryList(categoryId);
        if (CollectionUtils.isEmpty(kbCategories)) {
            return Collections.emptyList();
        }
        List<CustomerCategoryResponse> customerCategoryResponses = new ArrayList<>();
        kbCategories.forEach(kbCategory -> {
            customerCategoryResponses.add(buildCustomerCategoryToResponse(kbCategory));
        });
        return customerCategoryResponses;
    }

    @Override
    public void addCustomerKbCategory(CustomerKbCategoryAddRequest request) {
        KbCategory kbCategory = new KbCategory();
        BeanUtils.copyProperties(request, kbCategory);
        //暂时只开通一层
        kbCategory.setCategoryParentId(0);
        try {
            customerKbDao.insertCustomerKbCategory(kbCategory);
        } catch (Exception e) {
            throw new BusinessException("添加分类失败:{0}", e, request);
        }

    }

    @Override
    public void removeCustomerKbCategory(Integer categoryId) {
        customerKbDao.removeCustomerKbCategory(categoryId);
    }

    @Override
    public void addCustomerKbContent(CustomerKbContentAddRequest request) {
        KbCustomerArticle article = new KbCustomerArticle();
        BeanUtils.copyProperties(request, article);
        try {
            customerKbDao.insertKbCustomerArticle(article);
        } catch (Exception e) {
            throw new BusinessException("添加文档失败:{0}", e, request);
        }

    }

    @Override
    public void deleteCustomerKbContent(CustomerKbContentDeleteRequest request) {
        customerKbDao.deleteKbCustomerArticle(request.getArticleId());
    }

    @Override
    public CommonPageResponse<KbCustomerArticlePageQueryResponse> queryCustomerKbContentByCategoryId(CustomerKbContentPageQueryRequest request) {
        IPage<KbCustomerArticle> page = customerKbDao.selectKbCustomerArticleList(request.getCategoryId(), request.getPageNum(), request.getPageSize());
        return buildCustomerArticlePageResponse(page, customerKbDao);
    }


}
