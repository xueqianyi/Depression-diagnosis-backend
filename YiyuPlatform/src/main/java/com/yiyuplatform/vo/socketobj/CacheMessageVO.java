package com.yiyuplatform.vo.socketobj;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 需要推送到客户端的消息模板
 */
@Data
@AllArgsConstructor
public class CacheMessageVO {
    // websocket事件，用于前端监听
    private String event;
    private String receiverId;
    private Map<String,Object> body;
}
