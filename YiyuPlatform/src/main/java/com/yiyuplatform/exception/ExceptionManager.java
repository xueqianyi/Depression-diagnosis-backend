package com.yiyuplatform.exception;

import org.springframework.stereotype.Component;

/**
 * \* Date: 2021/11/24
 * \* Time: 22:59
 * \* Description:异常管理类
 * \
 */
@Component
public class ExceptionManager {

    public static CustomException create(String errorCode,String errorMsg) {
        return new CustomException(errorCode,errorMsg);
    }
}
