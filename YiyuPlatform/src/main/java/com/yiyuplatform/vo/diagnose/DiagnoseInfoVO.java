package com.yiyuplatform.vo.diagnose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * \* Date: 2021/11/29
 * \* Time: 22:10
 * \* Description:
 * \
 */
@Setter
@Getter
public class DiagnoseInfoVO {
    /**
     *  诊断表单状态
     */
    @JsonProperty("status")
    private int status;
    /**
     *  病人名字
     */
    @JsonProperty("patientName")
    private String patientName;
    /**
     *  诊断类型
     */
    @JsonProperty("type")
    private int type;
    /**
     *  医生名字
     */
    @JsonProperty("doctorName")
    private String doctorName;
    /**
     *  医生诊断意见
     */
    @JsonProperty("doctorSuggestion")
    private String doctorSug;
    /**
     *  提交时间
     */
    @JsonProperty("submitTime")
    private Timestamp submitTime;
    /**
     *  机器分析结束时间
     */
    @JsonProperty("analysisTime")
    private Timestamp anlTime;
    /**
     *  医生诊断时间
     */
    @JsonProperty("diagnoseTime")
    private Timestamp diTime;

    @JsonProperty("diagnoseId")
    private String diagnoseId;

    public DiagnoseInfoVO(int status, String patientName, int type, String doctorName, String doctorSug, Date submitTime, Date anlTime, Date diTime,String diagnoseId) {
        this.status = status;
        this.patientName = patientName;
        this.type = type;
        this.doctorName = doctorName;
        this.doctorSug = doctorSug;
        this.submitTime = new Timestamp(submitTime.getTime());
        this.anlTime = new Timestamp(anlTime.getTime());
        this.diTime = new Timestamp(diTime.getTime());
        this.diagnoseId=diagnoseId;
    }
}
