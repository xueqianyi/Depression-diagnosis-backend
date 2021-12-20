package com.yiyuplatform.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @description: 帖子评论entity
 * @author: Xue Qianyi
 * @date: 2021-12-07 10:31
 */
@Data
@Entity
@Table(name = "BBS_T_COMMENTS")
public class BbsTComments {

    @Column(name = "CO_ID")
    @Id
    private String coId;

    /**
     * 回帖所属主贴外键
     */
    @Column(name = "CO_POST_ID")
    private String coPostId;

    /**
     * 回帖内容
     */
    @Column(name = "CO_TEXT")
    private String coText;

    /**
     * 回帖时间
     */
    @Column(name = "CO_TIME")
    private Date coTime;

    /**
     * 回帖楼层
     */
    @Column(name = "CO_FLOOR")
    private Integer coFloor;

    /**
     * 回帖的获赞数
     */
    @Column(name = "CO_LIKES")
    private Integer coLikes;

    /**
     * 回帖用户主键
     */
    @Column(name = "CO_USERID")
    private String coUserid;

}
