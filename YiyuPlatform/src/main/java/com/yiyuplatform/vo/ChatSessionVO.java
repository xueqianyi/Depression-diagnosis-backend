package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatSessionVO {
    @JsonProperty("friendId")
    private String friendId;
    @JsonProperty("friendName")
    private String friendName;
    @JsonProperty("friendAvatar")
    private String friendAvatar;
    @JsonProperty("messageList")
    private List<MessageVO> messageVOList;

    public ChatSessionVO(String friendId, String friendName, String friendAvatar, List<MessageVO> messageVOList) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendAvatar = friendAvatar;
        this.messageVOList = messageVOList;
    }

    public ChatSessionVO(String friendId, String friendName, String friendAvatar) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendAvatar = friendAvatar;
        this.messageVOList= new ArrayList<>();
    }

}
