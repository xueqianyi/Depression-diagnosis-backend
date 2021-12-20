package com.yiyuplatform.service.search.factSearch.impl;

import com.yiyuplatform.entity.search.ItemForESKnowledge;
import com.yiyuplatform.service.search.ItemForESService;
import com.yiyuplatform.service.search.factSearch.KnowledgeSearchService;
import com.yiyuplatform.util.SearchResultVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 知识的搜索
 * @author: Xue Qianyi
 * @date: 2021-12-06 20:10
 */
@Service
@Slf4j
public class KnowledgeSearchServiceImpl implements KnowledgeSearchService {
    @Autowired
    private ItemForESService itemForESService;

    @Override
    public SearchResultVO search(String keyWords, String content, int page, int size) {
        //高亮信息
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("knoCardTitle").preTags("").postTags("");

        //构建条件对象
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        //必填选项--必定查询
        List<QueryBuilder> must = queryBuilder.must();
        must.add(QueryBuilders.matchQuery("knoCardTitle",keyWords));

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //条件
                .withQuery(queryBuilder)
                //高亮
                .withHighlightBuilder(highlightBuilder)
                //分页
                .withPageable(PageRequest.of(page,size)).build(); //一页2个

        //获取查询结果信息
        SearchHits<ItemForESKnowledge> search = itemForESService.search(query);
        log.info("查询到了多少个结果：" + search);
        List<ItemForESKnowledge> list = new ArrayList<>();
        for (SearchHit<ItemForESKnowledge> itemForESSearchHit : search) {
            ItemForESKnowledge itemForESKnowledge = itemForESSearchHit.getContent();
            //提前判断一下是否有高亮信息
            if(itemForESSearchHit.getHighlightFields().containsKey("knoCardTitle")){
                itemForESKnowledge.setKnoCardTitle(itemForESSearchHit.getHighlightFields().get("knoCardTitle").get(0));
            }
            list.add(itemForESKnowledge);
        }
        SearchResultVO searchResultVO = new SearchResultVO();
        searchResultVO.setHasMore(false);
        //判断是否还有多余的数据
        if(search.getTotalHits()>(page+1)*size){
            searchResultVO.setHasMore(true);
        }

        searchResultVO.setData(list);

        return searchResultVO;
    }
}
