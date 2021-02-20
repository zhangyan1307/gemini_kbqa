package com.gemini.admin.facade;

import com.gemini.admin.busiEnum.ErrorCodeEnum;
import com.gemini.admin.common.ApiResult;
import com.gemini.admin.exception.BusinessException;

/**
 * @Author: XXY
 * @Date: 2020/12/15 23:20
 */
public abstract class BaseFacade {
    protected ApiResult<Exception> exceptionHandler(Exception e){
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            return new ApiResult<>(businessException.getCode(),
                    businessException.getMessage());
        }else {
            return new ApiResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                    e.getMessage());
        }
    }
}
