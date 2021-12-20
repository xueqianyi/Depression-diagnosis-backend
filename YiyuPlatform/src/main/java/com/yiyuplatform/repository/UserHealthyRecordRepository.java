package com.yiyuplatform.repository;

import com.yiyuplatform.entity.UserHealthyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-01 23:12
 */
public interface UserHealthyRecordRepository extends JpaRepository<UserHealthyRecord,String> {
    //按照用户的openid查信息
    public UserHealthyRecord findByCiId(String ciId);

    //修改用户信息
    @Modifying(clearAutomatically = true)
    @Query("update UserHealthyRecord h set h.relation = ?1,h.userName = ?2,h.sex = ?3,h.userBirthday = ?4,h.userHeight = ?5,h.userWeight = ?6, " +
            "h.userBmi = ?7,h.diseaseHistory = ?8,h.smokeHistory = ?9 where h.ciId = ?10 ")
    @Transactional
    public void UpdateUserHealthyRecord(String relation, String userName, Integer sex, Date userBirthday, BigDecimal userHeight,
                                 BigDecimal userWeight, BigDecimal userBmi,String diseaseHistory,
                                 String smokeHistory, String ciId);



}
