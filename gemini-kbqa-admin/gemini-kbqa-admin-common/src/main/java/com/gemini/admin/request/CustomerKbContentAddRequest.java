package com.gemini.admin.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: XXY
 * @Date: 2021/1/13 23:32
 */
@Data
public class CustomerKbContentAddRequest {
    @ApiModelProperty("文档名称")
    private String articleName;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章分类id")
    private Integer articleCategoryId;

    @ApiModelProperty("文章描述")
    private String articleDesc;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章类型")
    private String articleType;




}
