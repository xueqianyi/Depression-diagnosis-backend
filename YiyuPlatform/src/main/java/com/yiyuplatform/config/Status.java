package com.yiyuplatform.config;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/11/13
 * \* Time: 12:58
 * \* Description:
 * \
 */


/**
 *  自定义状态
 */
public abstract class Status{
    /**
     * 诊断消息类型码
     */
    public static Integer DIAGNOSE_MSG=0;

    /**
     * 论坛消息类型码
     */
    public static Integer BBS_MSG=1;
    /**
     *  自定义错误码
     */
    @Getter
    public enum CustomErrors {
        CodeIsEmpty("1001", "code为空"),
        RequestError("1002", "请求异常"),
        SignatureError("1003", "签名校验错误"),
        ParamError("1005","请求参数错误"),
        DataNotExist("1010", "查询数据不存在"),
        PermissionDenied("1017", "权限不足"),
        TokenError("1085", "token错误"),
        DataBaseError("1089","数据库插入失败"),
        UnknownUser("1004","用户不存在");


        private String errorCode;
        private String errorMsg;

        CustomErrors(String code, String msg) {
            this.errorCode = code;
            this.errorMsg = msg;
        }
    }
    
    /**
     *  状态每局
     */
    public enum ExpireEnum{
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS)
        ;

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
