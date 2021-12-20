package com.yiyuplatform.vo.Doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-05 16:49
 */
@Data
public class DoctorDetailVO {
    @JsonProperty("docId")
    private String docId; //医生ID

    @JsonProperty("docAbstract")
    private String docAbstract; //医生简历

    @JsonProperty("docHospital")
    private String docHospital; //医生所属医院

    @JsonProperty("docPost")
    private String docPost;  //医生职位

    @JsonProperty("docCertification")
    private String docCertification; //认证

    @JsonProperty("docPhoto")
    private String docPhoto; //照片

    @JsonProperty("docDepartment")
    private String docDepartment; //部门

    @JsonProperty("realName")
    private String ciRealname; //真实姓名

    @JsonProperty("phoneNumber")
    private String ciPhonenumber; //电话号码

    @JsonProperty("sex")
    private Integer ciSex; //性别

    public void getCiPhonenumber(String ciPhonenumber) {
        this.ciPhonenumber = ciPhonenumber;
    }
}
