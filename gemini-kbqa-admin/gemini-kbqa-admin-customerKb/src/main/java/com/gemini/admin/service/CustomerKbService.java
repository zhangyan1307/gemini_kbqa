package com.gemini.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.dao.CustomerKbDaoImpl;
import com.gemini.admin.dto.CustomerKbCategoryDto;
import com.gemini.admin.entity.KbCategory;
import com.gemini.admin.entity.KbCustomerArticle;
import com.gemini.admin.request.CustomerKbCategoryAddRequest;
import com.gemini.admin.request.CustomerKbContentAddRequest;
import com.gemini.admin.request.CustomerKbContentDeleteRequest;
import com.gemini.admin.request.CustomerKbContentPageQueryRequest;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.CustomerCategoryResponse;
import com.gemini.admin.response.KbCustomerArticlePageQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author: XXY
 * @Date: 2020/12/14 0:27
 */
public interface CustomerKbService {

    /**
     * 获取分类列表
     * @param categoryId
     * @return
     */
    List<CustomerCategoryResponse> getCustomerKbCategoryList(Integer categoryId);

    /**
     * 添加分类
     * @param request
     */
    void addCustomerKbCategory(CustomerKbCategoryAddRequest request);

    /**
     * 删除分类
     * @param categoryId
     */
    void removeCustomerKbCategory(Integer categoryId);

    /**
     * 添加客服知识
     * @param request
     */
    void addCustomerKbContent(CustomerKbContentAddRequest request);

    /**
     * 删除客服知识
     * @param request
     */
    void deleteCustomerKbContent(CustomerKbContentDeleteRequest request);

    CommonPageResponse<KbCustomerArticlePageQueryResponse> queryCustomerKbContentByCategoryId(CustomerKbContentPageQueryRequest request);

    default CustomerCategoryResponse buildCustomerCategoryToResponse(KbCategory kbCategory){
        CustomerCategoryResponse response = new CustomerCategoryResponse();
        response.setCategoryId(kbCategory.getCategoryId());
        response.setCategoryName(kbCategory.getCategoryName());
        response.setCategoryParentId(kbCategory.getCategoryParentId());
        response.setLevel(kbCategory.getLevel());
        return response;
    }

    default CustomerKbCategoryDto buildCustomerCategoryToDto(KbCategory kbCategory){
        CustomerKbCategoryDto customerKbCategoryDto = new CustomerKbCategoryDto();
        customerKbCategoryDto.setCategoryId(kbCategory.getCategoryId());
        customerKbCategoryDto.setCategoryName(kbCategory.getCategoryName());
        customerKbCategoryDto.setCategoryParentId(kbCategory.getCategoryParentId());
        customerKbCategoryDto.setLevel(kbCategory.getLevel());
        return customerKbCategoryDto;
    }

    default CommonPageResponse<KbCustomerArticlePageQueryResponse> buildCustomerArticlePageResponse(IPage<KbCustomerArticle> page, CustomerKbDaoImpl dao){
        List<KbCustomerArticle> records = page.getRecords();
        CommonPageResponse<KbCustomerArticlePageQueryResponse> response = new CommonPageResponse<>();
        List<KbCustomerArticlePageQueryResponse> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(records)){
            response.setTotal(0);
            response.setData(list);
            return  response;
        }

        records.forEach(kbCustomerArticle -> {
            KbCustomerArticlePageQueryResponse kbCustomerArticlePageQueryResponse = new KbCustomerArticlePageQueryResponse();
            BeanUtils.copyProperties(kbCustomerArticle, kbCustomerArticlePageQueryResponse);
            //查询出文章分类名
            KbCategory kbCategory = dao.selectKbCategoryByCategoryId(kbCustomerArticle.getArticleCategoryId());
            if(Objects.nonNull(kbCategory)){
                kbCustomerArticlePageQueryResponse.setArticleCategory(kbCategory.getCategoryName());
            }
            list.add(kbCustomerArticlePageQueryResponse);
        });
        response.setTotal(page.getTotal().intValue());
        response.setData(list);
        response.setPageNum((int)page.getCurrent());
        response.setPageSize((int)page.getSize());
        return  response;
    }

}
