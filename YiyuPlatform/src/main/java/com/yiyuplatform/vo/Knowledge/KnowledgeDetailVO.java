package com.yiyuplatform.vo.Knowledge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-11-25 14:49
 */
@Data
public class KnowledgeDetailVO {
    @JsonProperty("articleId")
    private Integer knoCardId;  //知识卡ID
    @JsonProperty("artiletitle")
    private String knoCardTitle;//知识卡标题
    @JsonProperty("cat_id")
    private Integer categoryId;//分类ID
    @JsonProperty("cat_type")
    private Integer categoryType; //分类类型
    @JsonProperty("is_recommend")
    private Integer knoIshot;//是否是热门
    @JsonProperty("author")
    private String knoCardAuthor;//作者
    @JsonProperty("add_time")
    private Date knoCardReleaseDate;//发布日期
    @JsonProperty("look_count")
    private Integer knoCardRead;//阅读量
    @JsonProperty("likes")
    private Integer knoCardLikes;//点赞数
    @JsonProperty("content")
    private String knoCardContent;//正文
    @JsonProperty("summary")
    private String knoCardSummary;//摘要
    @JsonProperty("image")
    private String knoCardIcon;//图像




}
