package com.yiyuplatform.repository;

import com.yiyuplatform.entity.BbsTDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-07 3:45
 */
public interface BbsTDraftRepository extends JpaRepository<BbsTDraft,String> {

    //一个用户只能提交一个草稿，按照openId查询
    BbsTDraft findByOpenId(String openId);

    //更新草稿数据
    @Modifying(clearAutomatically = true)
    @Query("update BbsTDraft dd set dd.draftContent = ?1,dd.draftLocation = ?2,dd.draftTitle = ?3 where dd.openId = ?4")
    @Transactional
    void updateBbsTDraftByOpenId(String draftContent, String draftLocation, String draftTitle, String openId);

}
