package com.gemini.admin.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2020/12/13 23:24
 */
@Data
@ApiModel
public class CustomerCategoryResponse implements Serializable {
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
