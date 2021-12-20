package com.yiyuplatform.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author liyuxuan
 * @Date 2021/11/24 9:38
 * @Description
 */

public class ConsultForm {
    @Data
    public class  ThereIdOfConsult{  //咨询的双向信息
        /**
         * @Descriptions: 病人Id
         * */
        @JsonProperty("patient_id")
        private String patientId;
        /**
         * @Descriptions: 医生ID
         * */
        @JsonProperty("doctor_id")
        private int doctorId;

    }

}
