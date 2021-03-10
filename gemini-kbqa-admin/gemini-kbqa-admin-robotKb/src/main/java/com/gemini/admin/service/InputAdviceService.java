package com.gemini.admin.service; /**
 * Copyright (c) 2021,CHENGJIINFORMATION TECHNOLOGY(SHANGHAI) O.,LTD  All Rights Reserved.
 */

import com.gemini.admin.response.Response;

/**
 * @ClassName InputAdviceService
 * @Description 输入建议
 * @Author Gu YuLong
 * @Date 2021/3/10 9:42
 * @Version 1.0
 */
public interface InputAdviceService {

    Response queryQuestionByKeyWordFromEs(String keyword);

}