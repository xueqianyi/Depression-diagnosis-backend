package com.yiyuplatform.repository;


import com.yiyuplatform.entity.BbsTPostMain;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @Author: Drew
 * @Date: 2021/10/28 10:55
 *  BbsPostMain接口类
 */
public interface BbsTPostMainRepository extends JpaRepository<BbsTPostMain, String> {

    @Modifying
    @Query("update BbsTPostMain bbs set bbs.pReadNum=bbs.pReadNum+?2 where bbs.pId=?1")
    @Transactional
    void UpdateReadNum(String postId,Integer by);

    /**
     * 按照updateTime排序
     */
//    Sort.Order order = new Sort.Order(Sort.Direction.DESC, "age");

}
