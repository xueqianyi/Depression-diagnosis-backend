package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.exception.CustomException;
import com.yiyuplatform.service.Knowledge.KnowledgeService;
import com.yiyuplatform.service.authority.AuthorityService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 知识模块Controller
 * @author: Xue Qianyi
 * @date: 2021-11-25 11:27
 */
@RestController
@RequestMapping("/knowledge")
@Slf4j
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 获取首页热门知识接口
     * @return
     */

    @ApiOperation("获取首页热门知识接口")
    @GetMapping("/first_page")
    public Object getFirstPage(){
        log.info("用户开始请求首页>>>>>>>>>>>>>");
        return JSONObject.toJSON(knowledgeService.findHotKnowledgeVOByKnoIshot(1));

    }

    /**
     * 获取分类知识列表
     * @param categoryName
     * @return
     */

    @ApiOperation("获取分类知识列表")
    @GetMapping("/get_list")
    public Object getListByCategoryName( @RequestParam(value = "name",required = true,defaultValue = "生活") String categoryName){

        return JSONObject.toJSON(knowledgeService.findKnowledgeListByCategoryType(categoryName));
    }


    /**
     * 获取知识详情页
     * @param knoCardId
     * @return
     */
    @ApiOperation("获取知识详情页")
    @GetMapping("/detail")
    public Object findKnowledgeDetailByID(@RequestParam(value = "knowledgeId",required = true)Integer knoCardId){

        if(knowledgeService.findKnowledgeDetailByID(knoCardId) == null){
            throw new CustomException("1010","查询知识详情页不存在");
        }
        return JSONObject.toJSON(knowledgeService.findKnowledgeDetailByID(knoCardId));
    }


}
