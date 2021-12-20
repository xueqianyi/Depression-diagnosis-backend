package com.yiyuplatform.exception;

import com.yiyuplatform.vo.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * \* Date: 2021/11/24
 * \* Time: 23:24
 * \* Description:
 * \
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handlerException(Exception e) {

        //如果是自定义的异常，返回对应的错误信息
        if (e instanceof CustomException) {
            e.printStackTrace();
            CustomException exception = (CustomException) e;
            return CommonResult.error(exception.getErrorCode(), exception.getErrorMsg());
        } else {
            //如果不是已知异常，返回系统异常
            e.printStackTrace();
            return CommonResult.error("1000", "系统异常");
        }

    }

}
