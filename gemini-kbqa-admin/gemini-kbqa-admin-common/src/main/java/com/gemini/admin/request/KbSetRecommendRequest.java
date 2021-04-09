package com.gemini.admin.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XXY
 * @Date: 2021/3/21 9:04
 */
@Data
public class KbSetRecommendRequest implements Serializable {
    @ApiModelProperty("标准问id")
    private Long questionId;

    @ApiModelProperty
    private Integer recommendFlag;
}
