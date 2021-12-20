package com.yiyuplatform.service.search.impl;

import com.yiyuplatform.entity.search.ItemForESKnowledge;
import com.yiyuplatform.entity.search.ItemForESPost;
import com.yiyuplatform.service.search.ItemForESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-06 1:08
 */
@Slf4j
@Service
public class ItemForESServiceImpl implements ItemForESService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 批量插入Knowledge
     * @param itemForESKnowledgeList
     */
    @Override
    public void saveBatch(List<ItemForESKnowledge> itemForESKnowledgeList) {
        elasticsearchRestTemplate.save(itemForESKnowledgeList);
    }

    /**
     * 单条插入Knowledge
     * @param itemForESKnowledge
     */
    @Override
    public void save(ItemForESKnowledge itemForESKnowledge) {
        saveBatch(Arrays.asList(itemForESKnowledge));
    }

    /**
     * 搜索Knowledge
     * @param query
     * @return
     */
    @Override
    public SearchHits<ItemForESKnowledge> search(NativeSearchQuery query) {
        return elasticsearchRestTemplate.search(query, ItemForESKnowledge.class);
    }

    /**
     * 批量插入Post
     * @param itemForESPostList
     */
    @Override
    public void saveBatchForPost(List<ItemForESPost> itemForESPostList) {
        elasticsearchRestTemplate.save(itemForESPostList);
    }

    /**
     * 搜索Post
     * @param query
     * @return
     */
    @Override
    public SearchHits<ItemForESPost> searchPost(NativeSearchQuery query) {
        return elasticsearchRestTemplate.search(query, ItemForESPost.class);
    }
}
