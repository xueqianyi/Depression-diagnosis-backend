package com.yiyuplatform.service.UserHealthyRecord;

import com.yiyuplatform.entity.UserHealthyRecord;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-02 1:49
 */
public interface UserHealthyRecordService {
    //通过查找用户openid得到信息
    public UserHealthyRecord UserHealthyRecordByCiId(String ciId);
    //更新健康信息表
    public void UpdateUserHealthyRecord(String relation, String userName, Integer sex, Date userBirthday, BigDecimal userHeight,
                                        BigDecimal userWeight, BigDecimal userBmi, String diseaseHistory,
                                        String smokeHistory, String ciId);

    //插入健康信息表
    public void InsertHealthyRecord(String relation, String userName, Integer sex, Date userBirthday, BigDecimal userHeight,
                                    BigDecimal userWeight, BigDecimal userBmi, String diseaseHistory,
                                    String smokeHistory, String ciId);

    //删除健康信息表
    public void DeleteByCiId(String ciId);

    //判断用户是否插入过数据
    public boolean isFillTable(String openId);
}
