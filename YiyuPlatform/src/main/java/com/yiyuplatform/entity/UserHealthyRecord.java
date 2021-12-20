package com.yiyuplatform.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description: 用户信息
 * @author: Xue Qianyi
 * @date: 2021-12-01 22:44
 */
@Data
@Entity
@Table(name = "user_healthy_record")
public class UserHealthyRecord {
    @Id
    @Column(name = "HEALTHY_RECORD_ID")
    private String healthyRecordId;

    /**
     * 关联用户的openId
     */
    @Column(name = "CI_ID")
    private String ciId;

    /**
     * 与填写人的关系
     */
    @Column(name = "RELATION")
    private String relation;

    /**
     * 用户姓名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 用户性别
     */
    @Column(name = "SEX")
    private Integer sex;

    /**
     * 用户生日
     */
    @Column(name = "USER_BIRTHDAY")
    private Date userBirthday;

    /**
     * 用户身高
     */
    @Column(name = "USER_HEIGHT")
    private BigDecimal userHeight;

    /**
     * 用户体重
     */
    @Column(name = "USER_WEIGHT")
    private BigDecimal userWeight;

    /**
     * 用户BMI
     */
    @Column(name = "USER_BMI")
    private BigDecimal userBmi;

    /**
     * 用户病史描述
     */
    @Column(name = "DISEASE_HISTORY")
    private String diseaseHistory;

    /**
     * 用户吸烟史描述
     */
    @Column(name = "SMOKE_HISTORY")
    private String smokeHistory;


    /**
     * 修改时间
     */
    @Column(name = "UPDATE_TIME")
    private  Date updateTime;

}
