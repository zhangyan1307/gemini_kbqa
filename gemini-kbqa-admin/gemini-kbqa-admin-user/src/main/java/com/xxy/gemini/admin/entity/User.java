package com.xxy.gemini.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/3/23 0:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_customer")
public class User extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String nickName;

    private String csLevel;

    private String passWard;
}
