package com.yiyuplatform.form.diagnose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * \* Date: 2021/11/29
 * \* Time: 23:49
 * \* Description:
 * \
 */
@Data
public class ProgressCheckForm {
    @JsonProperty("openId")
    private String userId;
    @JsonProperty("form_id")
    private String formId;
}
