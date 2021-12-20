package com.yiyuplatform.vo.bbs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class BbsPostMainVO {
    @JsonProperty("postId")
    private String pId;
    @JsonProperty("postTitle")
    private String pTitle;
    @JsonProperty("postSubtime")
    private String pPubtime;
    @JsonProperty("postUpdateTime")
    private String  pUpdatetime;
    @JsonProperty("postMasterName")
    private String pMasterName;
    @JsonProperty("ifGood")
    private Integer pIfgood;
    @JsonProperty("ifTop")
    private Integer pIftop;
    @JsonProperty("readNum")
    private Integer pReadNum;

    public BbsPostMainVO(String pId, String pTitle, Date pPubtime, Date pUpdatetime, String pMasterName, Integer pIfgood, Integer pIftop, Integer pReadNum) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");//日期格
        this.pId = pId;
        this.pTitle = pTitle;
        this.pPubtime = sformat.format(pPubtime);
        this.pUpdatetime = sformat.format(pUpdatetime);
        this.pMasterName = pMasterName;
        this.pIfgood = pIfgood;
        this.pIftop = pIftop;
        this.pReadNum = pReadNum;
    }
}
