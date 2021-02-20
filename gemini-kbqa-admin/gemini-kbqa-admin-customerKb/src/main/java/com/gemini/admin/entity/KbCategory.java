package com.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2020/12/16 0:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_kb_category")
public class KbCategory extends BaseEntity{
    /**
     * 分类id
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类父id
     */
    private Integer categoryParentId;

    /**
     * 分类级别
     */
    private Integer level;
}
