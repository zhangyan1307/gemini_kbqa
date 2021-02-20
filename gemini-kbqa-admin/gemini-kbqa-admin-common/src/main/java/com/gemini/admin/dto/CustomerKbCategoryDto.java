package com.gemini.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2020/12/23 22:14
 */
@Data
public class CustomerKbCategoryDto implements Serializable {
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类父id
     */
    @ApiModelProperty(value = "分类父id")
    private Integer categoryParentId;

    /**
     * 分类级别
     */
    @ApiModelProperty(value = "分类级别")
    private Integer level;
}
