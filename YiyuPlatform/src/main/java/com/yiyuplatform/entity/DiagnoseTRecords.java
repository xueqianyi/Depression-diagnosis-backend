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
@Table ( name ="diagnose_t_records" )
@DynamicInsert
public class DiagnoseTRecords  implements Serializable {
	private static final long serialVersionUID =  3002994791564251917L;

	/**
	 * 诊断记录uuid，插入时使用uuid()生成id
	 */
   	@Column(name = "DI_ID",columnDefinition="varchar(36)")
	@Id
	private String diId;

	/**
	 * 患者ID
	 */
   	@Column(name = "DI_PATIENT_ID",columnDefinition="varchar(36)")
	private String diPatientId;

	/**
	 * 患者昵称
	 */
   	@Column(name = "DI_PATIENT_NKNAME",columnDefinition="varchar(16)")
	private String diPatientNkname;

	/**
	 * 诊断类型
	 */
   	@Column(name = "DI_TYPE",columnDefinition="int")
	private Integer diType;

	/**
	 * 表单状态1:分析中，2：分析完成未诊断 3：分析完成且已诊断 4：分析完成且已诊断且患者已确认
	 */
   	@Column(name = "DI_STATUS",columnDefinition="int")
	private Integer diStatus;

	/**
	 * 诊断医生ID
	 */
   	@Column(name = "DI_DOCTORID",columnDefinition="varchar(36)")
	private String diDoctorid;

	/**
	 * 医生诊断意见
	 */
   	@Column(name = "DI_DOCTOR_SUG",columnDefinition="text")
	private String diDoctorSug;

	/**
	 * 患者是否确认诊断结果（0,1）
	 */
   	@Column(name = "DI_ACK",columnDefinition="tinyint(1) default 0")
	private Integer diAck;

	/**
	 * 诊断提交时间
	 */
   	@Column(name = "DI_SUBMITTIME",columnDefinition="timestamp default now()")
	private Timestamp diSubmittime;

	/**
	 * 机器分析结束时间
	 */
   	@Column(name = "DI_ANLTIME",columnDefinition="timestamp")
	private Timestamp diAnltime;

	/**
	 * 医生诊断时间
	 */
   	@Column(name = "DI_DITIME",columnDefinition="timestamp")
	private Timestamp diDitime;

	/**
	 * 诊断结果url
	 */
   	@Column(name = "DI_ANL_RESULT",columnDefinition="varchar(200)")
	private String diAnlResult;

	/**
	 * 活动数据url
	 */
   	@Column(name = "DI_DATA_ACT",columnDefinition="varchar(200)")
	private String diDataAct;

	/**
	 * 音频数据url
	 */
   	@Column(name = "DI_DATA_AUD",columnDefinition="varchar(200)")
	private String diDataAud;

	/**
	 * 病人备注
	 */
   	@Column(name = "DI_PATIENT_COMMENT",columnDefinition="varchar(400)")
	private String diPatientComment;

}
