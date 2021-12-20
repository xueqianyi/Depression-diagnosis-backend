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
@Table ( name ="session_t_key" )
@DynamicInsert
public class SessionTKey  implements Serializable {
	private static final long serialVersionUID =  1133270918209773506L;

	/**
	 * 外键，该会话属于的用户id
	 */
   	@Column(name = "SE_UID",columnDefinition="varchar(36)")
	@Id
	private String seUid;

	/**
	 * 会话key
	 */
   	@Column(name = "SE_SESSION_KEY",columnDefinition="varchar(36)")
	private String seSessionKey;

	/**
	 * 返回给客户端的自定义登录态
	 */
   	@Column(name = "SE_SESSION_TOKEN",columnDefinition="varchar(36)")
	private String seSessionToken;

}
