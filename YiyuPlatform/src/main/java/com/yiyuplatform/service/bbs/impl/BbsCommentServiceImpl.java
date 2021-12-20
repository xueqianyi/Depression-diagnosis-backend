package com.yiyuplatform.service.bbs.impl;

import com.yiyuplatform.entity.BbsTComments;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.form.bbs.SubmitCommentForm;
import com.yiyuplatform.repository.BbsTCommentsRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.bbs.BbsCommentService;
import com.yiyuplatform.util.ListSortUtil;
import com.yiyuplatform.vo.bbs.BbsPostCommentVO;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/11/6
 * \* Time: 21:10
 * \* Description:
 * \
 */
@Service
@Slf4j
public class BbsCommentServiceImpl implements BbsCommentService {
    @Autowired
    private BbsTCommentsRepository bbsTCommentsRepository;
    @Autowired
    private TUserInfoRepository tUserInfoRepository;

    /**
     * 上传回复
     * @param submitCommentForm
     * @return
     */
    @Override
    public Object SubmitPostComment(SubmitCommentForm submitCommentForm) {
        //现在评论列表里查询是否是主贴的第一回帖
        List res = bbsTCommentsRepository.findByCoPostId(submitCommentForm.getCoPostId());

        if(res.size() == 0){
            //如果是第一回帖
            BbsTComments bbsTComments = new BbsTComments();
            //设置第一层楼
            bbsTComments.setCoFloor(1);
            //回复id
            String commentId= UUID.randomUUID().toString();
            bbsTComments.setCoId(commentId);
            //主贴ID
            bbsTComments.setCoPostId(submitCommentForm.getCoPostId());
            //回复内容
            bbsTComments.setCoText(submitCommentForm.getCoText());
            //回帖的用户的OPENID
            bbsTComments.setCoUserid(submitCommentForm.getCoUserid());

            //存入数据库
            bbsTCommentsRepository.save(bbsTComments);
        }else {
            //如果不是第一回帖
            BbsTComments bbsTComments = new BbsTComments();

            //先获取对应的楼层
            List<BbsTComments> bbsTCommentsList = bbsTCommentsRepository.findByCoPostId(submitCommentForm.getCoPostId());
            //排序
            ListSortUtil<BbsTComments> sortList = new ListSortUtil<BbsTComments>();
            sortList.sort(bbsTCommentsList, "coFloor", "desc");

            //最大楼层
            Integer maxFloor = bbsTCommentsList.get(0).getCoFloor();

            //当前回帖楼层为最大楼层+1
            bbsTComments.setCoFloor(maxFloor+1);
            //回帖的用户的OPENID
            bbsTComments.setCoUserid(submitCommentForm.getCoUserid());
            //回复id
            String commentId= UUID.randomUUID().toString();
            bbsTComments.setCoId(commentId);
            //主贴ID
            bbsTComments.setCoPostId(submitCommentForm.getCoPostId());
            //回复内容
            bbsTComments.setCoText(submitCommentForm.getCoText());

            //保存
            bbsTCommentsRepository.save(bbsTComments);
        }

        return null;
    }

    /**
     * 获取评论列表
     * @param postId
     * @return
     */
    @Override
    public List<BbsPostCommentVO> getCommentList(String postId) {

        log.info("进入了Service的获取评论列表------>>>>>>>");
        List<BbsPostCommentVO> bbsPostCommentVOList = new ArrayList<>();
        //按照postId查询回帖
        List<BbsTComments> bbsTCommentsList = bbsTCommentsRepository.findByCoPostId(postId);
        //如果目前没有回帖
        if(bbsTCommentsList == null){
            return null;
        }else{
            for (BbsTComments bbsTComments : bbsTCommentsList) {
                BbsPostCommentVO bbsPostCommentVO = new BbsPostCommentVO();
                //点赞数
                bbsPostCommentVO.setLikes(bbsTComments.getCoLikes());
                //楼层数
                bbsPostCommentVO.setFloor(bbsTComments.getCoFloor());
                //发布日期
                Date dd = bbsTComments.getCoTime();
                String date = dd.toString();
                date = date.substring(0,date.length() - 2);
                bbsPostCommentVO.setReleaseTime(date);
                //回帖内容
                bbsPostCommentVO.setText(bbsTComments.getCoText());
                //发帖人的openId
                String openId = bbsTComments.getCoUserid();
                //查询userInfo
                TUserinfo tUserinfo = new TUserinfo();
                tUserinfo = tUserInfoRepository.findByCiId(openId);
                //回帖人昵称
                bbsPostCommentVO.setNickName(tUserinfo.getCiNkname());
                //回帖人头像
                bbsPostCommentVO.setUserAvatarturl(tUserinfo.getCiAvatarturl());
                bbsPostCommentVOList.add(bbsPostCommentVO);
            }

            //按照楼层排序
            ListSortUtil<BbsPostCommentVO> sortList = new ListSortUtil<BbsPostCommentVO>();
            sortList.sort(bbsPostCommentVOList, "floor", "desc");
            return bbsPostCommentVOList;
        }
    }
}
