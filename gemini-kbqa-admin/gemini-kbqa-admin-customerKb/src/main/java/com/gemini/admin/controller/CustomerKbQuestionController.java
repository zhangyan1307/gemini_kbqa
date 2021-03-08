package com.gemini.admin.controller;

import com.gemini.admin.request.*;
import com.gemini.admin.response.CommonPageResponse;
import com.gemini.admin.response.KbCustomerQuestionPageQueryResponse;
import com.gemini.admin.response.Response;
import com.gemini.admin.service.CustomerKbQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: XXY
 * @Date: 2021/2/13 0:14
 */
@RestController
@Api(value = "问答知识库管理controller")
@AllArgsConstructor
@RequestMapping("/questionAndAnswer")
public class CustomerKbQuestionController {

    private final CustomerKbQuestionService customerKbQuestionService;

    @GetMapping("/getQuestionList")
    @ApiOperation("获取标准问答列表")
    public Response<CommonPageResponse<KbCustomerQuestionPageQueryResponse>>
        getQuestionList(KbCustomerQuestionPageQueryRequest request) {
        return Response.ok(customerKbQuestionService.queryCustomerKbQuestionByPage(request));
    }

    @PostMapping("/saveQuestion")
    @ApiOperation("增加标准问答")
    public Response<Void> saveQuestion(@RequestBody KbCustomerQuestionAddRequest request) {
        try {
            customerKbQuestionService.saveQuestion(request);
            return Response.ok();
        } catch (Exception e) {
            return Response.fail();
        }
    }

    @GetMapping("/deleteQuestion")
    @ApiOperation("删除标准问答")
    public Response<Void> deleteQuestion(KbCustomerQuestionDeleteRequest request) {
        try {
            customerKbQuestionService.deleteQuestion(request.getQuestionId());
            return Response.ok();
        } catch (Exception e) {
            return Response.fail();
        }
    }

    @GetMapping("/deleteSimilarityQuestion")
    @ApiOperation("删除相似问")
    public Response<Void> deleteSimilarityQuestion(KbCustomerSimilarityDeleteRequest request) {
        try {
            customerKbQuestionService.deleteSimilarityQuestion(request.getQuestionId());
            return Response.ok();
        } catch (Exception e) {
            return Response.fail();
        }
    }

    @GetMapping("/deleteRelationQuestion")
    @ApiOperation("删除关联问")
    public Response<Void> deleteRelationQuestion(KbCustomerRelationDeleteRequest request) {
        try {
            customerKbQuestionService.deleteRelationQuestion(request.getQuestionId());
            return Response.ok();
        } catch (Exception e) {
            return Response.fail();
        }
    }
}
