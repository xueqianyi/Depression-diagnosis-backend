package com.yiyuplatform.form.UserHealthyRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-02 15:19
 */
@Data
public class HealthyForm {
    /**
     * openId
     */
    private String ciId;
    /**
     * 关系
     */
    private String relation;
    /**
     * 真实姓名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 生日信息
     */
    private String userBirthday;
    /**
     * 用户身高
     */
    private double userHeight;
    /**
     * 用户体重
     */
    private double userWeight;
    /**
     * 用户BMI
     */
    private double userBmi;
    /**
     * 用户病史
     */
    private String diseaseHistory;
    /**
     * 用户吸烟史
     */
    private String smokeHistory;
}
