package com.yiyuplatform.form.diagnose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * \* Date: 2021/11/29
 * \* Time: 23:47
 * \* Description:
 * \
 */
@Data
public class SubmitSuggestionForm {

    /**
     * 医生id
     */
    @JsonProperty("doctorId")
    private String DoctorId;
    /**
     * 诊断表单Id
     */
    @JsonProperty("diagnose_id")
    private String DiagnoseId;
    /**
     * 诊断意见
     */
    @JsonProperty("suggestion")
    private String Suggestion;
}
