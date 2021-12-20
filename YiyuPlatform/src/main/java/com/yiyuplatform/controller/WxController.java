package com.yiyuplatform.controller;

import com.yiyuplatform.form.WxLoginForm;
import com.yiyuplatform.service.wx.WxService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/12
 * \* Time: 16:02
 * \* Description: 需要与微信服务器交互的业务的Controller
 * \
 */
@RestController
@Slf4j
@RequestMapping("api/wx")
public class WxController {
    @Autowired
    private WxService wxService;

    @ApiOperation(value = "用code置换微信OpenID和unionID")
    @ResponseBody
    @PostMapping(value = "wx_login")
    /**
     *  接受小程序发送的Code, 并发送给微信服务器，获取session_key,oppenid
     * @Param: [code, iv, encryptedData]
     * @return: java.lang.String
     */

    public Object wechatLogin(@RequestBody WxLoginForm wxLoginForm) {
        log.info("根据微信code获取微信信息--后台用户请求");
        return wxService.UserLogin(wxLoginForm);
    }

}
