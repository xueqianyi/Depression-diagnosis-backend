package com.yiyuplatform.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

/**
 * @description: 知识文章详情数据表
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:03
 */

@Data
@Entity
@Table(name = "kno_info")
public class KnoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer knoCardId;

    /**
     * 知识标文章题
     */
    @Column(name = "KNO_CARD_TITLE")
    private String knoCardTitle;

    /**
     * 文章类型
     */
    @Column(name = "CATEGORY_TYPE")
    private Integer categoryType;

    /**
     * 文章小图（封面）
     */
    @Column(name = "KNO_CARD_ICON")
    private String knoCardIcon;

    /**
     * 文章摘要
     */
    @Column(name = "KNO_CARD_SUMMARY")
    private String knoCardSummary;

    /**
     * 文章内容
     */
    @Column(name = "KNO_CARD_CONTENT")
    private String knoCardContent;

    /**
     * 文章作者
     */
    @Column(name = "KNO_CARD_AUTHOR")
    private String knoCardAuthor;

    /**
     * 文章阅读量
     */
    @Column(name = "KNO_CARD_READ")
    private Integer knoCardRead;

    /**
     * 文章点赞数
     */
    @Column(name = "KNO_CARD_LIKES")
    private Integer knoCardLikes;

    /**
     * 文章是否是热门 1--热门，0--非热门
     */
    @Column(name = "KNO_ISHOT")
    private Integer knoIshot;
    /**
     * 文章发布时间
     */
    @Column(name = "KNO_CARD_RELEASE_DATE")
    private Date knoCardReleaseDate;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "UPDATE_TIME")
    private  Date updateTime;
}
