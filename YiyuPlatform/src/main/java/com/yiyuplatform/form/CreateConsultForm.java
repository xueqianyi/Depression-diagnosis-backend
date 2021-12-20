package com.yiyuplatform.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateConsultForm {
    @JsonProperty("friendId")
    private String friendId;
}
