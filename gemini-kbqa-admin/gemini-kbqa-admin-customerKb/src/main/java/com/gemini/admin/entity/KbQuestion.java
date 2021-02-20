package com.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/9 16:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_questions")
public class KbQuestion extends BaseEntity {
    private Long id;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 分类id
     */
    private Integer categoryId;
}
