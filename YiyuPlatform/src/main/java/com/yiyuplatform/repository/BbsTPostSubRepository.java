package com.yiyuplatform.repository;

import com.yiyuplatform.entity.BbsTPostSub;
import com.yiyuplatform.vo.bbs.BbsPostCommentVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/28
 * \* Time: 8:12
 * \* Description: BbsPostSub接口类
 * \
 */
public interface BbsTPostSubRepository extends JpaRepository<BbsTPostSub, Integer> {

    int countBycoPostId(String postId);
}
