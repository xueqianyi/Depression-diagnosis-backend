package com.yiyuplatform.vo.bbs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

/**
 * @description: 返回帖子列表的单条VO
 * @author: Xue Qianyi
 * @date: 2021-12-06 22:00
 */

@Data
public class BbsPostListItemVO {
    //帖子Id
    private String pId;
    //用户头像
    private String ciAvatarturl;
    //帖子标题
    private String pTitle;
    //发帖日期
    private String createTime;
    //用户昵称
    private String ciNkname;
    //发帖地点
    private String pLocation;
    //帖子内容
    private String pContent;
    //帖子阅读量
    private Integer pReadNum;
    //帖子图片地址
    private String pImage;
}
