package com.yiyuplatform.repository;

import com.yiyuplatform.entity.BbsTComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 帖子评论
 * @author: Xue Qianyi
 * @date: 2021-12-07 10:41
 */
public interface BbsTCommentsRepository extends JpaRepository<BbsTComments,String> {

    //根据主贴ID查询评论
    public List<BbsTComments> findByCoPostId(String coPostId);


}
