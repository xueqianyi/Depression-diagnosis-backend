package com.yiyuplatform.form;

import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/25
 * \* Time: 11:52
 * \* Description: 小程序登录请求表单
 * \
 */
@Data
public class WxLoginForm {
    /**
     * 微信返回的code
     */
    private String code;
    /**
     * 非敏感的用户信息
     */
    private String rawData;
    /**
     * 签名信息
     */
    private String signature;
    /**
     * 加密的数据
     */
    private String encrypteData;
    /**
     * 加密密钥
     */
    private String iv;
}
