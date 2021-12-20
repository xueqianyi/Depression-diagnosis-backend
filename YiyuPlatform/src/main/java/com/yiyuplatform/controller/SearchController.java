package com.yiyuplatform.controller;

import com.yiyuplatform.service.search.factSearch.KnowledgeSearchService;
import com.yiyuplatform.service.search.factSearch.PostSearchService;
import com.yiyuplatform.util.SearchResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 搜索controller
 * @author: Xue Qianyi
 * @date: 2021-12-06 20:02
 */
@Slf4j
@RestController
@RequestMapping("api/search")
public class SearchController {
    @Autowired
    private KnowledgeSearchService knowledgeSearchService;
    @Autowired
    private PostSearchService postSearchService;

    /**
     * 获取知识搜索模块
     * @param keyWords
     * @param content
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("获取知识搜索模块")
    @GetMapping("/knowledge")
    public SearchResultVO searchKnowledge(String keyWords, String content, int page, @RequestParam(defaultValue = "5")int size ){
        log.info("开始搜索知识>>>>>>>>>>>>>>");
        return knowledgeSearchService.search(keyWords,content,page,size);
    }

    /**
     * 获取帖子搜索模块
     * @param keyWords
     * @param content
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("获取帖子搜索模块")
    @GetMapping("/post")
    public SearchResultVO searchPost(String keyWords, String content, int page, @RequestParam(defaultValue = "6")int size){
        log.info("开始搜索帖子>>>>>>>>>>>>>>");
        return postSearchService.searchForPost(keyWords,content,page,size);
    }
}
