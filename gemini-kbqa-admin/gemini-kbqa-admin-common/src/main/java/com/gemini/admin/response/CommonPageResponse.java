package com.gemini.admin.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2021/2/1 23:27
 */
@Data
public class CommonPageResponse<T> {
    private Integer pageSize;

    private Integer pageNum;

    private Integer total;

    private List<T> data;

}
