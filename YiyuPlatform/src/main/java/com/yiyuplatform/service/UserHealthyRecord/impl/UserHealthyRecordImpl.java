package com.yiyuplatform.service.UserHealthyRecord.impl;

import com.yiyuplatform.entity.UserHealthyRecord;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.repository.UserHealthyRecordRepository;
import com.yiyuplatform.service.UserHealthyRecord.UserHealthyRecordService;
import com.yiyuplatform.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-02 1:53
 */
@Service
@Slf4j
public class UserHealthyRecordImpl implements UserHealthyRecordService {

    @Autowired
    private UserHealthyRecordRepository userHealthyRecordRepository;
    @Autowired
    private TUserInfoRepository tUserInfoRepository;

    //按照微信openid查询健康信息表
    @Override
    public UserHealthyRecord UserHealthyRecordByCiId(String ciId) {
        return userHealthyRecordRepository.findByCiId(ciId);
    }

    //更新健康信息记录
    @Override
    public void UpdateUserHealthyRecord(String relation, String userName, Integer sex, Date userBirthday, BigDecimal userHeight, BigDecimal userWeight, BigDecimal userBmi, String diseaseHistory, String smokeHistory, String ciId) {
        tUserInfoRepository.updateWxUserInfoByHealthyRecord(ciId,userName,sex);
        userHealthyRecordRepository.UpdateUserHealthyRecord(relation,userName,sex,userBirthday,userHeight,userWeight,userBmi,diseaseHistory,smokeHistory,ciId);
    }

    //插入健康信息记录
    @Override
    public void InsertHealthyRecord(String relation, String userName, Integer sex, Date userBirthday, BigDecimal userHeight, BigDecimal userWeight, BigDecimal userBmi, String diseaseHistory, String smokeHistory, String ciId) {
        UserHealthyRecord userHealthyRecord = new UserHealthyRecord();

        //新生成的healthyRecordId;
        String healthyRecordId = KeyUtil.createUniqueKey();

        userHealthyRecord.setHealthyRecordId(healthyRecordId);
        userHealthyRecord.setCiId(ciId);
        userHealthyRecord.setDiseaseHistory(diseaseHistory);
        userHealthyRecord.setRelation(relation);
        userHealthyRecord.setSmokeHistory(smokeHistory);
        userHealthyRecord.setSex(sex);
        userHealthyRecord.setUserBirthday(userBirthday);
        userHealthyRecord.setUserBmi(userBmi);
        userHealthyRecord.setUserHeight(userHeight);
        userHealthyRecord.setUserWeight(userWeight);
        userHealthyRecord.setUserName(userName);
        userHealthyRecordRepository.save(userHealthyRecord);
    }

    //删除健康信息表
    @Override
    public void DeleteByCiId(String ciId) {
        UserHealthyRecord userHealthyRecord = userHealthyRecordRepository.findByCiId(ciId);
        //查询主键
        String id = userHealthyRecord.getHealthyRecordId();
        //删除数据
        userHealthyRecordRepository.deleteById(id);
    }

   //判断用户是否插入过健康信息表
    @Override
    public boolean isFillTable(String openId) {
        UserHealthyRecord userHealthyRecord = userHealthyRecordRepository.findByCiId(openId);
        if(userHealthyRecord == null){
            return false;
        }
        else{
            return true;
        }
    }

}
