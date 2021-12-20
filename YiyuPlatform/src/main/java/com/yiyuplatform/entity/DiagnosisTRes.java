package com.yiyuplatform.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @description: 诊断结果entity
 * @author: Xue Qianyi薛芊祎
 * @date: 2021-12-08 10:53
 */

@Data
@Entity
@Table(name = "diagnose_t_res")
public class DiagnosisTRes {

    @Id
    @Column(name = "DI_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diId;

    /**
     * 病人（用户）的openid
     */
    @Column(name = "DI_PATIENT_ID")
    private String diPatientId;

    /**
     * 诊断结果，是/不是抑郁症
     */
    @Column(name = "DI_RESULT")
    private Integer diResult;

    /**
     * 文件路径
     */
    @Column(name = "DI_USER_FILE")
    private String diUserFile;

    /**
     *文件ID
     */
    @Column(name = "DI_USER_FILE_ID")
    private String diUserFileId;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
}
