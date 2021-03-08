package com.gemini.admin.dao;

/**
 * @Author: XXY
 * @Date: 2021/2/9 16:48
 * 此类用于操作机器人问答知识库的dao操作
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.dao.mapper.KbCustomerQuestionMapper;
import com.gemini.admin.dao.mapper.KbCustomerRelationQuestionMapper;
import com.gemini.admin.dao.mapper.KbCustomerSimilarityQuestionMapper;
import com.gemini.admin.entity.KbCustomerArticle;
import com.gemini.admin.entity.KbQuestion;
import com.gemini.admin.entity.KbRelationQuestion;
import com.gemini.admin.entity.KbSimilarityQuestion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerKbQuestionDaoImpl {
    private final KbCustomerQuestionMapper kbCustomerQuestionMapper;

    private final KbCustomerRelationQuestionMapper kbCustomerRelationQuestionMapper;

    private final KbCustomerSimilarityQuestionMapper kbCustomerSimilarityQuestionMapper;

    /****操作标准问答库***/

    //根据分类id查询标准问答列表(分页）
    public IPage<KbQuestion> selectKbQuestionListPageByCategoryId(Integer categoryId, Integer pageSize, Integer pageNum){
        QueryWrapper<KbQuestion> queryWrapper = new QueryWrapper<>();
        if(categoryId != null){
            queryWrapper.eq("category_id", categoryId);
        };
        IPage<KbQuestion> questionIPage = new Page<>(pageNum, pageSize);

        return kbCustomerQuestionMapper.selectPage(questionIPage, queryWrapper);
    }

    /**
     * 保存标准问答
     * @param question
     */
    public Long saveKbQuestion(KbQuestion question){
        Integer row = kbCustomerQuestionMapper.insert(question);
        if(row == 0){
            log.warn("插入标准问答失败,{}", question);
        }
        return question.getId();
    }

    public void deleteKbQuestion(Long questionId){
        Integer row = kbCustomerQuestionMapper.deleteById(questionId);
        if(row == 0) {
            log.warn("删除标准问答失败：{}",questionId);
        }
    }

    /****操作相似问答库***/

    /**
     * 通过标准问id检索出相似问
     * @param questionId
     * @return
     */
    public List<KbSimilarityQuestion> selectSimilarityQuestionsByQuestionId(Long questionId){
        QueryWrapper<KbSimilarityQuestion> queryWrapper = new QueryWrapper<>();
        if(questionId != null){
            queryWrapper.eq("question_id", questionId);
        }
        return kbCustomerSimilarityQuestionMapper.selectList(queryWrapper);
    }

    /**
     * 添加相似问
     * @param question
     */
    public void saveSimilarityQuestion(KbSimilarityQuestion question){
        Integer row = kbCustomerSimilarityQuestionMapper.insert(question);
        if(row == 0){
            log.warn("插入相似问失败");
        }
    }

    /**
     * 删除相似问
     * @param id
     */
    public void deleteSimilarityQuestion(Long id){
        Integer row = kbCustomerSimilarityQuestionMapper.deleteById(id);
        if(row == 0){
            log.warn("删除相似问失败");
        }
    }

    /****操作关联问答库***/
    /**
     * 通过标准问id检索出关联问
     * @param questionId
     * @return
     */
    public List<KbRelationQuestion> selectRelationQuestionsByQuestionId(Long questionId){
        QueryWrapper<KbRelationQuestion> queryWrapper = new QueryWrapper<>();
        if(questionId != null){
            queryWrapper.eq("question_id", questionId);
        }
        return kbCustomerRelationQuestionMapper.selectList(queryWrapper);
    }

    /**
     * 添加关联问
     * @param question
     */
    public void saveRelationQuestion(KbRelationQuestion question){
        Integer row = kbCustomerRelationQuestionMapper.insert(question);
        if(row == 0){
            log.warn("新增关联问失败:{}", question);
        }
    }


    /**
     * 删除关联问
     * @param id
     */
    public void deleteRelationQuestion(Long id){
        Integer row = kbCustomerRelationQuestionMapper.deleteById(id);
        if(row == 0){
            log.warn("删除关联问失败:{}", id);
        }
    }

    public List<KbQuestion> selectRecommendQuestions(){
        QueryWrapper<KbQuestion> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend_flag", 1);
        return kbCustomerQuestionMapper.selectList(wrapper);
    }
}
