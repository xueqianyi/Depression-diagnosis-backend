package com.yiyuplatform.vo;

/**
 * \* Date: 2021/11/23
 * \* Time: 22:54
 * \* Description: 相应结果类，统一格式
 * \
 */
import lombok.Data;

@Data
public final class CommonResult<T> {

    protected int status = 1;

    protected String errorCode = "";

    protected String errorMsg = "";

    protected T data;

    public CommonResult() {
    }
    public CommonResult(String errorCode,String errorMsg){
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
        this.status=-1;
    }

    public CommonResult(T data) {
        this.data = data;
    }
    /**
     * 成功的返回
     * @param data 数据
     * @return 正常返回体
     */
    public static CommonResult success(Object data) {
        return new CommonResult(data);
    }

    /**
     * 错误返回
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     * @return 错误返回体
     */
    public static CommonResult error(String errorCode, String errorMessage) {
        return new CommonResult(errorCode,errorMessage);
    }
}
