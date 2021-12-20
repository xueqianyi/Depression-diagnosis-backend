package com.yiyuplatform.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * \* Date: 2021/11/16
 * \* Time: 22:58
 * \* Description:
 * \
 */
@Data
public class ConsultMsgForm {
    /**
     * 用户登录token
     */
    private String token;
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("message_type")
    private String msgType;
    @JsonProperty("message_content")
    private String msgContent;
    @JsonProperty("sender_id")
    private String senderId;


}
