package com.yiyuplatform.vo.Doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-05 16:58
 */
@Data
public class DoctorListVO {
    @JsonProperty("docId")
    private String docId; //医生ID
    @JsonProperty("phoneNumber")
    private String ciRealname; //真实姓名
    @JsonProperty("docPhoto")
    private String docPhoto; //照片
    @JsonProperty("docPost")
    private String docPost;  //医生职位
    @JsonProperty("docHospital")
    private String docHospital; //医生所属医院
    @JsonProperty("docDepartment")
    private String docDepartment; //部门
    @JsonProperty("sex")
    private Integer ciSex; //性别
}
