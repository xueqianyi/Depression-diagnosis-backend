package com.yiyuplatform.vo.socketobj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Drew
 * @Date: 10:55
 * @Description:
 */
@Data
public class ChatMessageVO {
    @JsonProperty("sessionKey")
    private String sessionKey;
    @JsonProperty("senderId")
    private String senderId;
    @JsonProperty("receiverId")
    private String receiverId;
    @JsonProperty("content")
    private String content;
}
