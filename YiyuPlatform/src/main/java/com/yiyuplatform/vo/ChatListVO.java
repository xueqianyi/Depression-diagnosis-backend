package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class ChatListVO {
    @JsonProperty("id1")
    private String id1;
    @JsonProperty("id2")
    private String id2;
    @JsonProperty("friendId")
    private String friendId;
    @JsonProperty("friendName")
    private String friendName;
    @JsonProperty("friendAvatar")
    private String friendAvatar;
    @JsonProperty("sessionKey")
    private String sessionKey;// 会话密钥
    @JsonProperty("lastMessage")
    private String lastMessage;
    @JsonProperty("lastTime")
    private String lastTime;

    public ChatListVO(String id1, String id2,String sessionKey, String lastMessage, Date lastTime) {
        String sdf1="";
        if(lastTime!=null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            sdf1 =f.format(lastTime);
        }
        this.id1=id1;
        this.id2=id2;
        this.sessionKey = sessionKey;
        this.lastMessage = lastMessage;
        this.lastTime = sdf1;
    }
}
