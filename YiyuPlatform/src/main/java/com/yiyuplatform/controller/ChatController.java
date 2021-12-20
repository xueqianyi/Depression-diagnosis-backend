package com.yiyuplatform.controller;

import com.yiyuplatform.form.CreateConsultForm;
import com.yiyuplatform.form.bbs.SubmitPostForm;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.consult.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "咨询接口")
@Slf4j
@RequestMapping("api/consult")
public class ChatController {
    @Autowired
    AuthorityService authorityService;
    @Autowired
    ChatService chatService;
    @ApiOperation("发起聊天")
    @RequestMapping(method = RequestMethod.POST,value = "/create_consult",produces = "application/json;charset=UTF-8")
    public Object CreateConsult(@RequestHeader String token , @RequestBody CreateConsultForm createConsultForm){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("用户"+openid+"发起聊天");
        return chatService.CreateConsult(createConsultForm.getFriendId(),openid);
    }
    @ApiOperation("查询聊天")
    @RequestMapping(method = RequestMethod.GET,value = "/query_consult",produces = "application/json;charset=UTF-8")
    public Object QueryChat(@RequestHeader String token,@RequestParam String sessionKey){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("用户"+openid+"查询聊天");
        return chatService.QuerySession(sessionKey,openid);
    }
    @ApiOperation("查询聊天列表")
    @RequestMapping(method = RequestMethod.GET,value = "/query_consult_list",produces = "application/json;charset=UTF-8")
    public Object QueryList(@RequestHeader String token){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("用户"+openid+"查询聊天");
        return chatService.QueryList(openid);
    }
}
