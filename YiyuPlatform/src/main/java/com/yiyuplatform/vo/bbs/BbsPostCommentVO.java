package com.yiyuplatform.vo.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;


import java.util.Date;

/**
 * 返给用户评论列表的VO
 */

@Data
public class BbsPostCommentVO{

    private String text; //评论内容
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String releaseTime; //发布时间
    private String nickName; //回帖人昵称
    private String userAvatarturl; //回帖人头像
    private Integer floor; //楼层数（回帖）
    private Integer likes; //点赞数

}
