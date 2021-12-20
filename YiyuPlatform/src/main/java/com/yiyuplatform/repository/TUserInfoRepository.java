package com.yiyuplatform.repository;

import com.yiyuplatform.entity.TUserinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙 薛芊祎
 * \* Date: 2021/10/28
 * \* Time: 8:11
 * \* Description: UserInfo接口类
 * \
 */
public interface TUserInfoRepository extends JpaRepository<TUserinfo, Integer> {
    /**
     * 根据用户id查找
     * @param openId
     * @return com.yiyuplatform.entity.TUserinfo
     */
    TUserinfo findByCiId(String openId);

    /**
     * 根据用户id更新用户微信相关信息
     * @param openId
     * @param nickName
     * @param gender
     * @param avatarUrl
     * @param timestamp
     * @return void
     */
    @Modifying(clearAutomatically = true)
    @Query("update TUserinfo user set user.ciNkname=?2,user.ciSex=?3,user.ciAvatarturl=?4,user.ciTimestamp=?5 where user.ciId=?1")
    void updateWxUserInfoByCiId(String openId,String nickName,Integer gender,String avatarUrl,Integer timestamp);

    /**
     * 通过健康信息表更新用户表
     * @param openId
     * @param realName
     * @param gender
     */
    @Modifying(clearAutomatically = true)
    @Query("update TUserinfo user set user.ciRealname=?2,user.ciSex=?3 where user.ciId=?1")
    void updateWxUserInfoByHealthyRecord(String openId,String realName,Integer gender);

}
