package com.yiyuplatform.form.diagnose;

import lombok.Data;

import java.util.Date;

/**
 * @description: 用户上传诊断文件的信息
 * @author: Xue Qianyi
 * @date: 2021-12-08 21:56
 */
@Data
public class UpdateFileInfo {
    /**
     * 病人openId
     */
    private String userId;

    /**
     * 提交时间
     */
    private String submitDate;
}
