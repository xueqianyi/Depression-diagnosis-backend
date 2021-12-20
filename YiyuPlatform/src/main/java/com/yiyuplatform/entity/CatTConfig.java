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
@Table ( name ="cat_t_config" )
@DynamicInsert
public class CatTConfig  implements Serializable {
	private static final long serialVersionUID =  7757135449411476586L;

	/**
	 * 诊断类型ID
	 */
   	@Column(name = "T_ID",columnDefinition="int")
	@Id
	private Integer tId;

	/**
	 * 诊断名字
	 */
   	@Column(name = "T_NAME",columnDefinition="varchar(12)")
	private String tName;

}
