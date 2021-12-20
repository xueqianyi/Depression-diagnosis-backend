package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.form.bbs.SubmitCommentForm;
import com.yiyuplatform.form.bbs.SubmitPostForm;
import com.yiyuplatform.form.bbs.SubmitReply;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.bbs.BbsCommentService;
import com.yiyuplatform.service.bbs.BbsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Xue Qianyi
 * @Date: 21:21
 *  论坛Controller
 */
@RestController
@Slf4j
@RequestMapping("api/bbs")
public class BbsController {
    @Autowired
    BbsService bbsService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    BbsCommentService bbsCommentService;

    /**
     * 获取帖子列表
     * @return
     */
    @ApiOperation("获取帖子列表")
    @GetMapping("/look_post_list")
    public Object LookPostList(){
        log.info("开始获取帖子列表>>>>>>>>>>>>");
        return JSONObject.toJSON(bbsService.lookPostList());
    }


    /**
     * 发布帖子(带有图片上传)
     * @param token
     * @param submitPostForm
     * @param image
     * @return
     */
    @ApiOperation("发布帖子(带有图片上传)")
    @RequestMapping(method = RequestMethod.POST,value = "/submit_post",consumes = "multipart/form-data") //,produces = "application/json;charset=UTF-8"
    @ResponseBody
    public Object SubmitPost(@RequestHeader String token ,SubmitPostForm submitPostForm, @RequestParam("file")MultipartFile image){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("发帖的其余信息"+submitPostForm);
        log.info("开始发布帖子>>>>>>>>>>>>");
        return JSONObject.toJSON(bbsService.SubmitPost(submitPostForm,image));
    }


    @ApiOperation("回帖zhl")
    @PostMapping("/reply_post")
    public Object ReplyPost(@RequestHeader String token, @RequestBody SubmitReply submitReply){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return JSONObject.toJSON(bbsService.ReplyPost(submitReply));
    }

    @ApiOperation("发布回帖")
    @RequestMapping(method = RequestMethod.POST,value = "/comments/submit_comment",produces = "application/json;charset=UTF-8")
    public Object SubmitPostComment(@RequestHeader String token ,@RequestBody SubmitCommentForm submitCommentForm){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("开始发布回帖>>>>>>>>>>>>");
        return JSONObject.toJSON(bbsCommentService.SubmitPostComment(submitCommentForm));
    }


    /**
     * 获取评论列表
     * @param postId
     * @return
     */
    @ApiOperation("获取评论列表")
    @GetMapping("/comments/list")
    public Object findPostCommentsListByPostId(@RequestParam(value = "postId",required = true)String postId) {
        log.info("开始请求评论列表");
        return JSONObject.toJSON(bbsCommentService.getCommentList(postId));
    }

}
