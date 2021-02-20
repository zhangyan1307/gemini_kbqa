package com.gemini.admin.request;

import com.gemini.admin.response.CommonPageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XXY
 * @Date: 2021/2/12 13:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KbCustomerQuestionPageQueryRequest extends CommonPageResponse {
    private Integer categoryId;
}
