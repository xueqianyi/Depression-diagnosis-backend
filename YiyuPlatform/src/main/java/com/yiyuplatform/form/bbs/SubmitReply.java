package com.yiyuplatform.form.bbs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SubmitReply {
    @JsonProperty("openId")
    private String openId;

    @JsonProperty("postId")
    private String postId;

    @JsonProperty("content")
    private String content;
}
