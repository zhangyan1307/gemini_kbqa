package com.gemini.admin.controller;

import com.gemini.admin.response.CustomerCategoryResponse;
import com.gemini.admin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/12/13 22:25
 */
@RestController("/customerKb")
@Api(value = "客服知识库管理controller")
public class CustomerKbController {

    @GetMapping("/getCustomerCategories")
    @ApiOperation(value = "获取客服知识库分类")
    public Response getCustomerCategories(){
        return new Response<List<CustomerCategoryResponse>>().ok(new ArrayList<>());
    }
}
