package com.yiyuplatform.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @description: 诊断文件entity
 * @author: Xue Qianyi
 * @date: 2021-12-08 22:22
 */
@Data
@Entity
@Table(name = "diagnose_file")
public class DiagnoseFile {
    @Id
    @Column(name = "DF_ID")
    private String dfId;

    /**
     * 病人用户的openId
     */
    @Column(name = "DF_PATIENT_ID")
    private String dfPatinetId;

    /**
     * 病人提交时间
     */
    @Column(name = "DF_SUBMIT_TIME")
    private String dfSubmitTime;

    /**
     * 录入数据库时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 病人提交的文件路径
     */
    @Column(name = "DF_FILE_PATH")
    private String dfFilePath;  //文件路径



}
