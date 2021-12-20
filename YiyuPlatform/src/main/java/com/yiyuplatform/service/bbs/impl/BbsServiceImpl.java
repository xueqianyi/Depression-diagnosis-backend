package com.yiyuplatform.service.bbs.impl;

        import com.alibaba.fastjson.JSONObject;
        import com.yiyuplatform.config.RedisConfig;
        import com.yiyuplatform.config.Status;
        import com.yiyuplatform.config.WxConfig;
        import com.yiyuplatform.entity.BbsTDraft;
        import com.yiyuplatform.entity.BbsTPostMain;
        import com.yiyuplatform.entity.BbsTPostSub;
        import com.yiyuplatform.entity.TUserinfo;
        import com.yiyuplatform.exception.ExceptionManager;
        import com.yiyuplatform.form.bbs.SubmitPostForm;
        import com.yiyuplatform.form.bbs.SubmitReply;
        import com.yiyuplatform.repository.BbsTDraftRepository;
        import com.yiyuplatform.repository.BbsTPostMainRepository;
        import com.yiyuplatform.repository.BbsTPostSubRepository;
        import com.yiyuplatform.repository.TUserInfoRepository;
        import com.yiyuplatform.service.bbs.BbsService;
        import com.yiyuplatform.util.RedisUtil;
        import com.yiyuplatform.util.UploadUtils;
        import com.yiyuplatform.vo.bbs.BbsPostCommentVO;
        import com.yiyuplatform.vo.bbs.BbsPostListItemVO;
        import com.yiyuplatform.vo.bbs.BbsPostMainVO;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;
        import org.springframework.web.multipart.MultipartFile;

        import javax.transaction.Transactional;
        import java.sql.Date;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;
        import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙 Xue Qianyi
 * \* Date: 2021/11/6
 * \* Time: 21:09
 * \* Description:
 * \
 */
@Service
@Slf4j

public class BbsServiceImpl implements BbsService {
    @Autowired
    BbsTPostMainRepository bbsMainRepo;
    @Autowired
    BbsTPostSubRepository bbsSubRepo;
    @Autowired
    TUserInfoRepository tUserInfoRepository;
    @Autowired
    BbsTDraftRepository bbsTDraftRepository;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    WxConfig wxConfig;


    @Override
    @Transactional
    /**
     * 发帖
     * @param submitPostForm
     * @return java.lang.Object
     */
    public Object SubmitPost(SubmitPostForm submitPostForm, MultipartFile image) {
        try {
            //调用工具类上传图片
            String imagePath = UploadUtils.upload(image);
            log.info("图片地址名字：>>>>>>>>>" + imagePath);
            BbsTPostMain newPost = new BbsTPostMain();
            String postId = UUID.randomUUID().toString();
            newPost.setPId(postId);
            newPost.setPMasterid(submitPostForm.getOwnerId());
            newPost.setPTitle(submitPostForm.getTitle());
            newPost.setPContent(submitPostForm.getContent());
            newPost.setCreateTime(submitPostForm.getCreateTime());
            newPost.setPLocation(submitPostForm.getLocation());
            //上传图片
            newPost.setPImage(imagePath);
            bbsMainRepo.save(newPost);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Transactional
    @Override
    /**
     * 回帖
     * @param submitReply
     * @return java.lang.Object
     */
    public Object ReplyPost(SubmitReply submitReply) {
        try {
            BbsTPostSub newReply = new BbsTPostSub();
            int floorNums = bbsSubRepo.countBycoPostId(submitReply.getPostId());
            newReply.setCoId(UUID.randomUUID().toString());
            newReply.setCoPostId(submitReply.getPostId());
            newReply.setCoText(submitReply.getContent());
            newReply.setCoUserid(submitReply.getOpenId());
            newReply.setCoFloor(floorNums + 1);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    @Transactional
    /**
     * 获取帖子列表+图片
     * @return
     */
    public List<BbsPostListItemVO> lookPostList() {
        try {
            //按照最新顺序排序
            List<BbsTPostMain> bbsTPostMainList = bbsMainRepo.findAll(Sort.by(Sort.Direction.DESC, "updateTime"));

            List<BbsPostListItemVO> bbsPostListItemVOS = new ArrayList<>();
            for (BbsTPostMain bbsTPostMain : bbsTPostMainList) {
                //查找发帖人的openId
                String openid = bbsTPostMain.getPMasterid();
                //查找对应的userInfo
                TUserinfo tUserinfo = tUserInfoRepository.findByCiId(openid);

                //封装BbsPostListItem
                BbsPostListItemVO bbsPostListItemVO = new BbsPostListItemVO();

                //头像
                bbsPostListItemVO.setCiAvatarturl(tUserinfo.getCiAvatarturl());
                //昵称
                bbsPostListItemVO.setCiNkname(tUserinfo.getCiNkname());
                //发布日期
                bbsPostListItemVO.setCreateTime(bbsTPostMain.getCreateTime());
                //地点信息
                bbsPostListItemVO.setPLocation(bbsTPostMain.getPLocation());
                //标题
                bbsPostListItemVO.setPTitle(bbsTPostMain.getPTitle());
                //ID
                bbsPostListItemVO.setPId(bbsTPostMain.getPId());
                //阅读量
                bbsPostListItemVO.setPReadNum(bbsTPostMain.getPReadNum());
                //内容
                bbsPostListItemVO.setPContent(bbsTPostMain.getPContent());

                String imagePath = "http://192.168.43.202:8080/" + bbsTPostMain.getPImage();
                //图片
                bbsPostListItemVO.setPImage(imagePath);

                bbsPostListItemVOS.add(bbsPostListItemVO);
            }
            //逆序返回
            //Collections.reverse(bbsPostListItemVOS);

            return bbsPostListItemVOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    @Override
//    @Transactional
//    /**
//     * 存储保存草稿,点击保存按钮
//     */
//    public Object savePostDraft(SubmitPostForm submitPostForm) {
//        try{
//            //查询用户是否保存过草稿
//            BbsTDraft bbsTDraft = new BbsTDraft();
//            bbsTDraft = bbsTDraftRepository.findByOpenId(submitPostForm.getOwnerId());
//
//            //如果用户没有保存过草稿，则存储
//            //注：updateTime自动生成
//            if(bbsTDraft == null){
//                BbsTDraft needSaveDraft = new BbsTDraft();
//                //草稿创建时间
//                needSaveDraft.setCreateTime(submitPostForm.getCreateTime());
//                //草稿ID
//                String id = UUID.randomUUID().toString();
//                needSaveDraft.setDraftId(id);
//                //创建草稿的用户
//                needSaveDraft.setOpenId(submitPostForm.getOwnerId());
//                //发帖地点
//                needSaveDraft.setDraftLocation(submitPostForm.getLocation());
//                //标题
//                needSaveDraft.setDraftTitle(submitPostForm.getTitle());
//                //内容
//                needSaveDraft.setDraftContent(submitPostForm.getContent());
//
//                //保存数据
//                bbsTDraftRepository.save(needSaveDraft);
//            }else {
//                //如果用户有过草稿，取草稿,更新草稿
//                bbsTDraftRepository.updateBbsTDraftByOpenId(submitPostForm.getContent(),submitPostForm.getLocation(),submitPostForm.getTitle(),submitPostForm.getOwnerId());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            throw e;
//        }
//        return null;
//    }
//}
}
