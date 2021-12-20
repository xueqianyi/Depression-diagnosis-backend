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
@Table ( name ="t_doctor" )
@DynamicInsert
public class TDoctor  implements Serializable {
	private static final long serialVersionUID =  5646228365205969786L;

	/**
	 * 医生id
	 */
   	@Column(name = "DOC_ID",columnDefinition="varchar(36)")
	@Id
	private String docId;

	/**
	 * 医生个人简介
	 */
   	@Column(name = "DOC_ABSTRACT",columnDefinition="longtext")
	private String docAbstract;

	/**
	 * 医生所属医院
	 */
   	@Column(name = "DOC_HOSPITAL",columnDefinition="varchar(50)")
	private String docHospital;

	/**
	 * 医生职称
	 */
	@Column(name = "DOC_POST",columnDefinition="varchar(50)")
	private String docPost;

	/**
	 * 医生认证
	 */
	@Column(name = "DOC_CERTIFICATION",columnDefinition="varchar(100)")
	private String docCertification;

	/**
	 * 医生相片
	 */
	@Column(name = "DOC_PHOTO",columnDefinition="varchar(500)")
	private String docPhoto;

	/**
	 * 医生科室
	 */
	@Column(name = "DOC_DEPARTMENT")
	private String docDepartment;

}
