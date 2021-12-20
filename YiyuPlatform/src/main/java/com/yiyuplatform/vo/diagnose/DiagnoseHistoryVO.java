package com.yiyuplatform.vo.diagnose;

import lombok.Data;

/**
 * @description: 某用户诊断历史记录
 * @author: Xue Qianyi
 * @date: 2021-12-09 0:26
 */
@Data
public class DiagnoseHistoryVO {
    private Integer id;
    private String userAvatar; //用户头像
    private String realName;  //用户真实姓名
    private String updateTime; //用户更新时间
    private Integer diagnoseResult; //诊断结果
    private Integer sex; //用户性别
}
