package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/29
 * \* Time: 11:51
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
public class UserInfoVO {
    @JsonProperty("openid")
    private String ciId;
    @JsonProperty("nickName")
    private String ciNkname;
    @JsonProperty("personType")
    private Integer personType;
    @JsonProperty("realName")
    private String realName;
    @JsonProperty("IDNumber")
    private String IdNumber;
    @JsonProperty("gender")
    private Integer sex;
    @JsonProperty("phone")
    private String ciPhonenumber;
    @JsonProperty("avatarUrl")
    private String avatarUrl;
    @JsonProperty("level")
    private Integer ciLevel;
    @JsonProperty("status")
    private Integer status;
}
