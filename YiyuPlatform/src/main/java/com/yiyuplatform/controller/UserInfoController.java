package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/25
 * \* Time: 11:55
 * \* Description: UserInfo的控制层
 * \
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "个人中心api")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 用户查询
     * @param token 用户登录态
     * @param userId 用户id
     * @return java.lang.Object
     */
    @GetMapping("/user_retrieve")
    @ResponseBody
    @ApiOperation("用户查询")
    public Object UserRetrieve(@RequestHeader("token") String token, @RequestParam(value = "openId") String userId)
    {
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return JSONObject.toJSON(userInfoService.RetrieveUser(userId));
    }
    @GetMapping("/diagnose_history_retrieve")
    @ResponseBody
    @ApiOperation("诊断历史查询")
    public Object DiagnoseHistoryRetrieve(@RequestHeader("token") String token, @RequestParam(value = "openId") String userId){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return userInfoService.RetrieveDiagnose(userId);
    }
}
