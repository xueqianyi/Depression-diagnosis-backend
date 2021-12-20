package com.yiyuplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/29
 * \* Time: 14:02
 * \* Description:发生错误时，同意按照此格式返回相应信息
 * \
 */
@Data
@AllArgsConstructor
public class ErrorVO {
    private String code;
    private String msg;
}
