package com.yiyuplatform.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @description: 帖子保存草稿entity
 * @author: Xue Qianyi
 * @date: 2021-12-07 3:41
 */
@Data
@Entity
@Table(name = "BBS_T_DRAFT")
public class BbsTDraft {
    @Column(name = "DRAFT_ID")
    @Id
    private String draftId;


    /**
     * 创建草稿人
     */
    @Column(name = "OPEN_ID")
    private String openId;

    /**
     * 内容
     */
    @Column(name = "DRAFT_CONTENT")
    private String draftContent;

    /**
     * 标题
     */
    @Column(name = "DRAFT_TITLE")
    private String draftTitle;

    /**
     * 地点
     */
    @Column(name = "DRAFT_LOCATION")
    private String draftLocation;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "UPDATE_TIME")
    private  Date updateTime;

}
