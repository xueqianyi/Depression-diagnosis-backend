package com.yiyuplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

/**
 * @Description  
 * @Author dalong 
 * @Date 2021-11-29 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="t_userinfo" )
@DynamicInsert
public class TUserinfo  implements Serializable {
	private static final long serialVersionUID =  2704086312202388748L;

	/**
	 * 用户微信OpenID，在插入数据使用uuid()生成ID
	 */
   	@Column(name = "CI_ID",columnDefinition="varchar(36)")
	@Id
	private String ciId;

	/**
	 * 用户昵称，微信昵称
	 */
   	@Column(name = "CI_NKNAME",columnDefinition="varchar(20)")
	private String ciNkname;

	/**
	 * 用户类型(面向用户 1.普通用户 2.医生)
	 */
   	@Column(name = "CI_PERSONTYPE",columnDefinition="int")
	private Integer ciPersontype;

	/**
	 * 用户真实姓名
	 */
   	@Column(name = "CI_REALNAME",columnDefinition="varchar(16)")
	private String ciRealname;

	/**
	 * 用户身份证号
	 */
   	@Column(name = "CI_ID_CARD",columnDefinition="varchar(20)")
	private String ciIdCard;

	/**
	 * 用户手机号
	 */
   	@Column(name = "CI_PHONENUMBER",columnDefinition="varchar(16)")
	private String ciPhonenumber;

	/**
	 * 用户性别 0-男性 1-女性
	 */
   	@Column(name = "CI_SEX",columnDefinition="tinyint")
	private Integer ciSex;

	/**
	 * 注册日期
	 */
   	@Column(name = "CI_SIGNUPDATE",columnDefinition="timestamp default now()")
	private Timestamp ciSignupdate;

	/**
	 * 用户状态（1.已认证 2.未认证 3.封禁）
	 */
   	@Column(name = "CI_STATUS",columnDefinition="int")
	private Integer ciStatus;

	/**
	 * 用户头像（url）
	 */
   	@Column(name = "CI_AVATARTURL",columnDefinition="varchar(400)")
	private String ciAvatarturl;

	/**
	 * 用户等级（面向系统 1.普通用户/2.普通管理员/3.超级管理员）
	 */
   	@Column(name = "CI_LEVEL",columnDefinition="int")
	private Integer ciLevel;

	/**
	 * 敏感数据获取的时间戳, 开发者可以用于数据时效性校验
	 */
   	@Column(name = "CI_TIMESTAMP",columnDefinition="int")
	private Integer ciTimestamp;

}
