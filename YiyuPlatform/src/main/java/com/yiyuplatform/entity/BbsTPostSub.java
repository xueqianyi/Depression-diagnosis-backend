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

import java.sql.Timestamp;

/**
 * @Description  
 * @Author dalong 
 * @Date 2021-11-29 
 */

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="bbs_t_post_sub" )
public class BbsTPostSub  implements Serializable {
	private static final long serialVersionUID =  3152241139804835283L;

	/**
	 * 回帖uuid,插入时使用uuid()生成uuid
	 */
   	@Column(name = "CO_ID",columnDefinition="varchar(36)")
	@Id
	private String coId;

	/**
	 * 回帖所属主贴的uuid,外键
	 */
   	@Column(name = "CO_POST_ID",columnDefinition="varchar(36)")
	private String coPostId;

	/**
	 * 回帖内容,前端限制长度
	 */
   	@Column(name = "CO_TEXT",columnDefinition="text")
	private String coText;

	/**
	 * 回帖时间
	 */
   	@Column(name = "CO_TIME",columnDefinition="timestamp")
	private Timestamp coTime;

	/**
	 * 回帖人id
	 */
   	@Column(name = "CO_USERID",columnDefinition="varchar(36)")
	private String coUserid;

	/**
	 * 楼层数
	 */
	@Column(name="CO_FLOOR",columnDefinition = "int")
	private Integer coFloor;
}
