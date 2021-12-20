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
@Table ( name ="message_t_type" )
@DynamicInsert
public class MessageTType  implements Serializable {
	private static final long serialVersionUID =  2864538719484276874L;

	/**
	 * 种类ID
	 */
   	@Column(name = "MT_ID",columnDefinition="int")
	@Id
	private Integer mtId;

	/**
	 * 种类名字
	 */
   	@Column(name = "MT_NAME",columnDefinition="varchar(12)")
	private String mtName;

}
