package com.yiyuplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Description  发帖主贴entity
 * @Author dalong 
 * @Date 2021-11-29 
 */


@Data
@EqualsAndHashCode
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="bbs_t_post_main" )
public class BbsTPostMain  implements Serializable {
	private static final long serialVersionUID =  124127381903287730L;

	/**
	 * 主贴uuid,插入时使用uuid()生成id
	 */
   	@Column(name = "P_ID",columnDefinition="varchar(36)")
	@Id
	private String pId;

	/**
	 * 帖子标题
	 */
   	@Column(name = "P_TITLE",columnDefinition="text")
	private String pTitle;

	/**
	 * 帖子发表时间
	 */
   	@Column(name = "CREATE_TIME",columnDefinition="timestamp default now()")
	private String createTime;

	/**
	 * 帖子更新时间（最后一次跟帖时间）
	 */
   	@Column(name = "UPDATE_TIME",columnDefinition="timestamp")
	private Date updateTime;

	/**
	 * 贴主uuid,外键
	 */
   	@Column(name = "P_MASTERID",columnDefinition="varchar(36)")
	private String pMasterid;

	/**
	 * 是否为精华帖
	 */
   	@Column(name = "P_IFGOOD",columnDefinition="tinyint(1)")
	private Integer pIfgood;

	/**
	 * 是否置顶
	 */
   	@Column(name = "P_IFTOP",columnDefinition="tinyint(1)")
	private Integer pIftop;

	/**
	 * 浏览量
	 */
   	@Column(name = "P_READ_NUM",columnDefinition="int")
	private Integer pReadNum;

	/**
	 * 帖子内容
	 */
	@Column(name = "P_CONTENT",columnDefinition="varchar(400)")
	private String pContent;

	/**
	 * 发帖地点
	 */
	@Column(name = "P_LOCATION",columnDefinition = "varchar(255)")
	private String pLocation;

	/**
	 * 发帖图片（1张）
	 */
	@Column(name = "P_IMAGE",columnDefinition = "varchar(500")
	private String pImage;
}
