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
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="user_t_log" )
@DynamicInsert
public class UserTLog  implements Serializable {
	private static final long serialVersionUID =  1486464584401424583L;

	/**
	 * 记录uuid,插入时使用uuid()生成
	 */
   	@Column(name = "L_ID",columnDefinition="varchar(36)")
	@Id
	private String lId;

	/**
	 * 用户id，外键
	 */
   	@Column(name = "L_USERID",columnDefinition="varchar(36)")
	private String lUserid;

	/**
	 * 操作类型，登入/登出
	 */
   	@Column(name = "L_OPER_TYPE",columnDefinition="varchar(4)")
	private String lOperType;

	/**
	 * 操作时间
	 */
   	@Column(name = "L_TIME",columnDefinition="timestamp")
	private Timestamp lTime;

}
