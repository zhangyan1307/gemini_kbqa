package com.gemini.admin.controller;

import com.gemini.admin.request.CustomerKbCategoryAddRequest;
import com.gemini.admin.response.CustomerCategoryResponse;
import com.gemini.admin.response.Response;
import com.gemini.admin.service.CustomerKbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/12/13 22:25
 */
@RestController
@Api(value = "客服知识库管理controller")
@AllArgsConstructor
@RequestMapping("/customerKb")
public class CustomerKbController {
    private final CustomerKbService customerKbService;

    @GetMapping("/getCustomerCategories")
    @ApiOperation(value = "获取客服知识库分类")
    public Response getCustomerCategories(@ApiParam("父类id") Integer parentId) {
        return Response.ok(customerKbService.getCustomerKbCategoryList(parentId));
    }

    @PostMapping("/saveCustomerCategory")
    @ApiOperation(value = "添加客服知识库分类")
    public Response<Void> saveCustomerCategory(@RequestBody CustomerKbCategoryAddRequest request){
        customerKbService.addCustomerKbCategory(request);
        return Response.ok();
    }

    @DeleteMapping("/removeCustomerCategory")
    @ApiOperation(value = "删除客服知识库分类")
    public Response<Void> removeCustomerCategory(@ApiParam("分类id") Integer categoryId){
        customerKbService.removeCustomerKbCategory(categoryId);
        return Response.ok();
    }

}
