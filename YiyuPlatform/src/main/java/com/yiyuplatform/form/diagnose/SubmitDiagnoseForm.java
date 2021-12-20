package com.yiyuplatform.form.diagnose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * \* Date: 2021/11/29
 * \* Time: 23:46
 * \* Description:
 * \
 */
@Data
public class SubmitDiagnoseForm {
    /**
     * @Descriptions: 诊断类型
     */
    @JsonProperty(value = "openId", required = true)
    private String openId;

    @JsonProperty(value = "patientComment")
    private String patientComment;

    @JsonProperty("activityUrl")
    private String activityUrl;

    @JsonProperty("audioUrl")
    private String audioUrl;

}
