package com.yiyuplatform.service.wx;

import com.yiyuplatform.form.WxLoginForm;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/12
 * \* Time: 19:29
 * \* Description:
 * \
 */
public interface WxService {
    Object UserLogin(WxLoginForm wxLoginForm);
}
