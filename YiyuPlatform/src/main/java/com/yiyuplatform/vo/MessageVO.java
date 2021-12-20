package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 聊条信息对象
 */
@Data
@NoArgsConstructor
public class MessageVO{
    @JsonProperty("sendTime")
    private String sendTime;
    // 0为自己，1为朋友
    @JsonProperty("flag")
    private Integer flag;
    @JsonProperty("content")
    private String content;
    public MessageVO(Date sendTime, String content, String checkId,String sendId) {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sdf1 = f.format(sendTime);
        this.sendTime=sdf1;
        if(checkId.equals(sendId)){
            this.flag=0;
        }else{
            this.flag=1;
        }
        this.content = content;
    }
}