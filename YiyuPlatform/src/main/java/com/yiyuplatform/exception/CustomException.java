package com.yiyuplatform.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* Date: 2021/11/24
 * \* Time: 22:57
 * \* Description:自定义异常类
 * \
 */
@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private String errorCode;

    private String errorMsg;

}
