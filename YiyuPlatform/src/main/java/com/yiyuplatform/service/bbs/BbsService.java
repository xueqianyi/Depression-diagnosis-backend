package com.yiyuplatform.service.bbs;

import com.yiyuplatform.entity.BbsTPostMain;
import com.yiyuplatform.form.bbs.SubmitPostForm;
import com.yiyuplatform.form.bbs.SubmitReply;
import com.yiyuplatform.vo.bbs.BbsPostListItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙 薛芊祎
 * \* Date: 2021/11/6
 * \* Time: 21:09
 * \* Description:
 * \
 */
public interface BbsService {


    //上传帖子
    Object SubmitPost(SubmitPostForm submitPostForm, MultipartFile image);

    Object ReplyPost(SubmitReply submitReply);

    //查看帖子列表(带详情）
    public List<BbsPostListItemVO> lookPostList();



}
