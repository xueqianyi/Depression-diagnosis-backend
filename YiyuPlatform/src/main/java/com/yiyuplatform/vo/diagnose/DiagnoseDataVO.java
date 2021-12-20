package com.yiyuplatform.vo.diagnose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 诊断数据视图
 * @Author: Drew
 * @Date: 9:21
 */
public class DiagnoseDataVO {
    @JsonProperty("riskRatio")
    private String riskRatio;
}
