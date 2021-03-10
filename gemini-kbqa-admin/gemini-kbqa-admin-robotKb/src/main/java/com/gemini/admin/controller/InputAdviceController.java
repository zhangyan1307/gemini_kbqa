package com.gemini.admin.controller; /**
 * Copyright (c) 2021,CHENGJIINFORMATION TECHNOLOGY(SHANGHAI) O.,LTD  All Rights Reserved.
 */

import com.gemini.admin.response.Response;
import com.gemini.admin.service.InputAdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName InputAdviceController
 * @Description 输入建议
 * @Author Gu YuLong
 * @Date 2021/3/10 9:41
 * @Version 1.0
 */
@RestController
@Api(value = "输入建议")
@RequestMapping("/robotKb")
@Slf4j
public class InputAdviceController {

    @Autowired
    private InputAdviceService inputAdviceService;


    @GetMapping("/queryCustomerContentByCategoryId")
    @ApiOperation("根据关键字从ES查询问题")
    public Response queryQuestionByKeyWordFromEs(@RequestParam("keyword") String keyword) {
        return inputAdviceService.queryQuestionByKeyWordFromEs(keyword);
    }

}
