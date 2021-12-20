package com.yiyuplatform.service.search;

import com.yiyuplatform.entity.search.ItemForESKnowledge;
import com.yiyuplatform.entity.search.ItemForESPost;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-06 1:07
 */
public interface ItemForESService {

    /**
     * 批量插入Knowledge
     */
    void saveBatch(List<ItemForESKnowledge> itemForESKnowledgeList);

    /**
     * 单条插入Knowledge
     */
    void save(ItemForESKnowledge itemForESKnowledge);

    /**
     * 搜索Knowledge
     */
    SearchHits<ItemForESKnowledge> search(NativeSearchQuery query);

    /**
     * 批量插入Post
     */
    void saveBatchForPost(List<ItemForESPost> itemForESPostList);

    /**
     * 搜索Post
     */
    SearchHits<ItemForESPost> searchPost(NativeSearchQuery query);
}
