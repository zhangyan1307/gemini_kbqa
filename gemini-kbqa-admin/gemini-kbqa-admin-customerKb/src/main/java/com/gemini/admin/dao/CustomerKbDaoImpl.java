package com.gemini.admin.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.dao.mapper.KbCategoryMapper;
import com.gemini.admin.dao.mapper.KbCustomerArticleMapper;
import com.gemini.admin.entity.KbCategory;
import com.gemini.admin.entity.KbCustomerArticle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: XXY
 * @Date: 2020/12/16 0:22
 * 此类用于dao层基本操作实体类，同样也用于操作其他数据源
 */
@Service
@AllArgsConstructor
@Slf4j
public class CustomerKbDaoImpl {

    private final KbCategoryMapper kbCategoryMapper;

    private final KbCustomerArticleMapper kbCustomerArticleMapper;

    public List<KbCategory> selectKbCategoryList(Integer categoryId){
        categoryId = Optional.ofNullable(categoryId).orElse(0);
        QueryWrapper<KbCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_parent_id", categoryId);
        return kbCategoryMapper.selectList(wrapper);
    }

    public void insertCustomerKbCategory(KbCategory kbCategory){
        Integer row = kbCategoryMapper.insert(kbCategory);
        if(row == 0){
            log.warn("客服知识分类添加失败:{}", kbCategory);
        }
    }

    public void removeCustomerKbCategory(Integer categoryId){
        Integer row = kbCategoryMapper.deleteById(categoryId);
        if(row == 0){
            log.warn("删除分类失败:{}", categoryId);
        }
    }

    public IPage<KbCustomerArticle> selectKbCustomerArticleList(Integer categoryId, Integer pageNum, Integer pageSize){
        QueryWrapper<KbCustomerArticle> queryWrapper = new QueryWrapper<>();
        if(categoryId != null){
            queryWrapper.eq("article_category_id", categoryId);
        };
        IPage<KbCustomerArticle> articleIPage = new Page<>(pageNum, pageSize);
        return kbCustomerArticleMapper.selectPage(articleIPage, queryWrapper);
    }


    public KbCategory selectKbCategoryByCategoryId(Integer categoryId){
        if(categoryId == null){
            return null;
        }
        return kbCategoryMapper.selectById(categoryId);
    }

    public void insertKbCustomerArticle(KbCustomerArticle kbCustomerArticle){
        Integer row = kbCustomerArticleMapper.insert(kbCustomerArticle);
        if(row == 0){
            log.warn("添加客服知识库失败：{}", kbCustomerArticle);
        }
    }

    public void deleteKbCustomerArticle(Integer id){
        Integer row = kbCustomerArticleMapper.deleteById(id);
        if(row == 0){
            log.warn("删除文档失败:{}", id);
        }
    }
}
