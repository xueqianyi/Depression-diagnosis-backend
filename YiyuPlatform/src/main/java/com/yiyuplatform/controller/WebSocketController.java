package com.yiyuplatform.controller;

import com.yiyuplatform.form.ConsultMsgForm;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.websocket.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/6
 * \* Time: 20:26
 * \* Description:
 * \
 */
@Slf4j
@Api(tags = "通信接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class WebSocketController {
    /**
     *  通信接口
     */
    private final WebSocketService webSocketService;
    private final AuthorityService authorityService;


    @ApiOperation("发送消息")
    @GetMapping("send")
    /**
     *  实例API
     */
    public String send(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("date", LocalDateTime.now().toString());
        int size = webSocketService.sendMsg(map);
        return "发送成功";
    }


}
