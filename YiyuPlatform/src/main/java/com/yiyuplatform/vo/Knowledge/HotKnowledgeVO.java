package com.yiyuplatform.vo.Knowledge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-11-24 19:30
 */

@ApiModel("首页热门知识列表返回对象")
@Data
public class HotKnowledgeVO {

    @ApiModelProperty(value="知识卡ID", dataType = "int")
    private Integer knoCardId;

    @ApiModelProperty(value="文章标题", dataType = "String")
    private String knoCardTitle;

    @ApiModelProperty(value="知识卡ID", required = true, dataType = "int")
    private String categoryName;

    @ApiModelProperty(value="知识卡ID", required = true, dataType = "int")
    private Date knoCardReleaseDate;

    @ApiModelProperty(value="知识卡ID", required = true, dataType = "int")
    private String knoCardIcon;

    @ApiModelProperty(value="知识卡ID", required = true, dataType = "int")
    private Integer knoCardRead;

    @ApiModelProperty(value="知识卡ID", required = true, dataType = "int")
    private Integer knoCardLikes;
}
