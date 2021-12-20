package com.yiyuplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;

/**
 * generated by Generate POJOs.groovy 
 * <p>Date: 2021-12-08 19:41:00.</p>
 *
 * @author custom
 */

@Table ( name ="chat_t_records" )
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@DynamicInsert
public class ChatTRecords implements Serializable {
	private static final long serialVersionUID =  6013221855346156241L;

	/**
	 * 消息uuid,插入时使用uuid()生成Id
	 */
   	@Column(name = "M_ID" )
	@Id
	@ApiModelProperty( value="消息uuid,插入时使用uuid()生成Id")
	private String mId;

	/**
	 * 消息内容，前端另写代码限制长度。若消息内容为文件，则为内容为文件Url
	 */
   	@Column(name = "M_TEXT" )
	@ApiModelProperty( value="消息内容，前端另写代码限制长度。若消息内容为文件，则为内容为文件Url")
	private String mText;

	/**
	 * 0-成功 1-失败
	 */
   	@Column(name = "M_STATUS" )
	@ApiModelProperty( value="0-成功 1-失败")
	private Integer mStatus;

	/**
	 * 发送时间
	 */
   	@Column(name = "M_SENDTIME" )
	@ApiModelProperty( value="发送时间")
	private Timestamp mSendtime;

	/**
	 * 外键，消息类型 1.文本 2.图片 3.文件
	 */
   	@Column(name = "M_TYPE" )
	@ApiModelProperty( value="外键，消息类型 1.文本 2.图片 3.文件")
	private Integer mType;

	/**
	 * 发送者ID，外键
	 */
   	@Column(name = "M_SENDERID" )
	@ApiModelProperty( value="发送者ID，外键")
	private String mSenderid;

	/**
	 * 外键，聊天记录所属会话
	 */
   	@Column(name = "M_SESSIONKEY" )
	@ApiModelProperty( value="外键，聊天记录所属会话")
	private String mSessionkey;

}
