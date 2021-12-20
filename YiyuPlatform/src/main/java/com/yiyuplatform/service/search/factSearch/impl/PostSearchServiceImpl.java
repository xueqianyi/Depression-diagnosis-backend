package com.yiyuplatform.service.search.factSearch.impl;

import com.yiyuplatform.entity.search.ItemForESPost;
import com.yiyuplatform.service.search.ItemForESService;
import com.yiyuplatform.service.search.factSearch.PostSearchService;
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
 * @description: 帖子的搜索
 * @author: Xue Qianyi
 * @date: 2021-12-07 2:38
 */
@Service
@Slf4j
public class PostSearchServiceImpl implements PostSearchService {
    @Autowired
    private ItemForESService itemForESService;

    @Override
    public SearchResultVO searchForPost(String keyWords, String content, int page, int size) {
        //高亮信息
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("knoCardTitle").preTags("").postTags("");

        //构建条件对象
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        //必填选项--必定查询
        List<QueryBuilder> must = queryBuilder.must();
        must.add(QueryBuilders.matchQuery("pTitle",keyWords));
      //  must.add(QueryBuilders.matchQuery("pLocation",keyWords));

        //选填项--选择查询
        List<QueryBuilder> should = queryBuilder.should();
        should.add(QueryBuilders.matchQuery("pLocation",content));
//        should.add(QueryBuilders.matchQuery("pTitle",content));
//        should.add(QueryBuilders.matchQuery("ciNkname",content));

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //条件
                .withQuery(queryBuilder)
                //高亮
                .withHighlightBuilder(highlightBuilder)
                //分页
                .withPageable(PageRequest.of(page,size)).build(); //一页2个

        //获取查询结果信息
        SearchHits<ItemForESPost> search = itemForESService.searchPost(query);
        log.info("搜索的帖子数目>>>>>>>>" + search);
        List<ItemForESPost> list = new ArrayList<>();
        for (SearchHit<ItemForESPost> itemForESSearchHit : search) {
            ItemForESPost itemForESPost = itemForESSearchHit.getContent();
            //提前判断一下是否有高亮信息
            if(itemForESSearchHit.getHighlightFields().containsKey("pTitle")){
                itemForESPost.setPTitle(itemForESSearchHit.getHighlightFields().get("pTitle").get(0));
            }

            //特别修改了图片路径
            String imagePath = "http://192.168.43.202:8080/"+itemForESPost.getImageUrl();
            itemForESPost.setImageUrl(imagePath);

            list.add(itemForESPost);
        }
        //System.out.println(list);

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
