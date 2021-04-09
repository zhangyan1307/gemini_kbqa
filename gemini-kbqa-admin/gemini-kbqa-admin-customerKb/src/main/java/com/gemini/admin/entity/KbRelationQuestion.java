package com.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/9 16:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_relation_questions")
public class KbRelationQuestion extends BaseEntity {
    private Long id;

    /**
     * 标准问id
     */
    private Long questionId;

    /**
     * 关联问
     */
    private String relationQuestion;

    /**
     * 关联问答案
     */
    private String relationAnswer;

}
