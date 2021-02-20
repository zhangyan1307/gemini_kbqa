package com.gemini.admin.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: XXY
 * @Date: 2020/12/24 8:58
 */
@Data
public class CustomerKbCategoryAddRequest {

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
