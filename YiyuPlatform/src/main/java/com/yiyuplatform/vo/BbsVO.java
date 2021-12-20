package com.yiyuplatform.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: Drew
 * @Date: 21:26
 *  论坛相关视图
 */
public class BbsVO {
    public class BbsPost {
        @JsonProperty("title")
        private String title;
        @JsonProperty("update_time")
        private String updateTime;
        @JsonProperty("owner_id")
        private String ownerId;
        @JsonProperty("if_good")
        private boolean ifGood;
        @JsonProperty("read_num")
        private int readNum;
    }
}
