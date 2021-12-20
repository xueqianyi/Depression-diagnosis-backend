package com.yiyuplatform.form.bbs;

import lombok.Data;


/**
 * @description: 发主贴的表单
 * @author: Xue Qianyi
 * @date: 2021-12-07 10:42
 */

@Data
public class SubmitPostForm {
    /**
     * 帖子标题
     */
private String title;

    /**
     * 发帖人openId
     */
    private String ownerId;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发帖日期
     */
    private String createTime;

    /**
     * 发帖地点
     */
    private String location;


    /**
     * 发帖图片
     */
   // private MultipartFile image;
}
