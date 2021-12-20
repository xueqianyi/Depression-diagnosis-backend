package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author liyuxuan
 * @Date 2021/11/23 14:07
 * @Description
 */
public class DoctorVO {
    public class FeedbackVO {
        /**
         *  医生基本信息
         */
        @JsonProperty("doctor_id")
        private String doctorId;
        @JsonProperty("doctor_name")
        private String doctorName;
    }
}
