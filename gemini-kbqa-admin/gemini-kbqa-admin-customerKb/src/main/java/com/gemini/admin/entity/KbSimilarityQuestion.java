package com.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/9 16:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_similarity_questions")
public class KbSimilarityQuestion extends BaseEntity{
    private Long id;

    /**
     * 标准问id
     */
    private Long questionId;

    /**
     * 相似问
     */
    private String similarityQuestion;

    /**
     * 相似答
     */
    private String similarityAnswer;

}
