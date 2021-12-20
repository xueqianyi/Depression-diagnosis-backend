package com.yiyuplatform.service.bbs;

import com.yiyuplatform.entity.BbsTComments;
import com.yiyuplatform.form.bbs.SubmitCommentForm;
import com.yiyuplatform.vo.bbs.BbsPostCommentVO;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/11/6
 * \* Time: 21:10
 * \* Description:
 * \
 */
public interface BbsCommentService {
    //上传评论，回复帖子
    Object SubmitPostComment(SubmitCommentForm submitCommentForm);

    //返回评论列表
    List<BbsPostCommentVO> getCommentList(String postId);

}
