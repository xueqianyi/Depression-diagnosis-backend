package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @Author liyuxuan
 * @Date 2021/11/23 14:40
 * @Description
 */
public class ConsultHistoryVO {
    public class FeedbackVO {
        /**
         *  咨询历史
         */
        @JsonProperty("doctor_id")
        private String doctorId;
        @JsonProperty("doctor_name")
        private String doctorName;
        @JsonProperty("patient_id")
        private String patientId;
        @JsonProperty("patient_name")
        private String patientName;
        @JsonProperty("consult_time")
        private Date consultTime;
    }
}
