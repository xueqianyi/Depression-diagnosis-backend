package com.yiyuplatform.form.bbs;

import lombok.Data;

/**
 * @description: 回帖的表单
 * @author: Xue Qianyi
 * @date: 2021-12-07 10:42
 */
@Data
public class SubmitCommentForm {
    /**
     * 主贴ID
     */
    private String coPostId;

    /**
     * 回复人的openId
     */
    private String coUserid;

    /**
     * 回复内容
     */
    private String coText;
}
