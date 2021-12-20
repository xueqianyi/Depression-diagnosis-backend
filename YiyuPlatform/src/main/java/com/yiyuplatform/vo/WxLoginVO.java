package com.yiyuplatform.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/12
 * \* Time: 15:58
 * \* Description: 用户登录返回对象
 * \
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class WxLoginVO {
    @JsonProperty("token")
    private String token;
    @JsonProperty("openId")
    private String id;
    @JsonProperty("user_info")
    private JSONObject usefInfo;
}
