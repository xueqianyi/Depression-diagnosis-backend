package com.yiyuplatform.vo.socketobj;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Drew
 * @Date: 0:01
 * @Description: 诊断推送消息
 */
@Data
public class PushMsgVO {
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("content")
    private String content;
}
