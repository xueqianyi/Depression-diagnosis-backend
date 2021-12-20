package com.yiyuplatform.service.search.impl;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.entity.BbsTPostMain;
import com.yiyuplatform.entity.KnoCategory;
import com.yiyuplatform.entity.KnoInfo;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.entity.search.ItemForESKnowledge;
import com.yiyuplatform.entity.search.ItemForESPost;
import com.yiyuplatform.repository.BbsTPostMainRepository;
import com.yiyuplatform.repository.KnoCategoryRepository;
import com.yiyuplatform.repository.KnoInfoRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.search.ItemForESService;
import com.yiyuplatform.vo.bbs.BbsPostListItemVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-06 1:12
 */
@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ItemForESKnowledgeServiceImplTest {

    @Autowired
    private KnoInfoRepository knoInfoRepository;
    @Autowired
    private KnoCategoryRepository knoCategoryRepository;
    @Autowired
    private ItemForESService itemForESService;
    @Autowired
    private BbsTPostMainRepository bbsTPostMainRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private TUserInfoRepository tUserInfoRepository;

    //知识搜索数据装载
    @Test
    public void testSaveKnowledgeInfo(){
        List<KnoInfo> knoInfoList = knoInfoRepository.findAll();
        List<ItemForESKnowledge> itemForESKnowledgeList = new ArrayList<>();
        for (KnoInfo knoInfo : knoInfoList) {
            //获取类型
            Integer type = knoInfo.getCategoryType();

            //获取类别名称
            KnoCategory knoCategory = knoCategoryRepository.findByCategoryType(type);
            String typeName = knoCategory.getCategoryName();

            ItemForESKnowledge itemForESKnowledge = new ItemForESKnowledge();
            itemForESKnowledge.setCategoryName(typeName);
            itemForESKnowledge.setKnoCardAuthor(knoInfo.getKnoCardAuthor());
            itemForESKnowledge.setKnoCardContent(knoInfo.getKnoCardContent());
            itemForESKnowledge.setKnoCardIcon(knoInfo.getKnoCardIcon());
            itemForESKnowledge.setKnoCardId(knoInfo.getKnoCardId());
            itemForESKnowledge.setKnoCardLikes(knoInfo.getKnoCardLikes());
            itemForESKnowledge.setKnoCardRead(knoInfo.getKnoCardRead());
            itemForESKnowledge.setKnoCardTitle(knoInfo.getKnoCardTitle());
            itemForESKnowledge.setKnoIshot(knoInfo.getKnoIshot());

            itemForESKnowledgeList.add(itemForESKnowledge);
        }
        itemForESService.saveBatch(itemForESKnowledgeList);
    }

    //帖子数据装载
    @Test
    public void testSavePostInfo(){

        List<BbsTPostMain> bbsTPostMainsList = bbsTPostMainRepository.findAll();

        //返回的帖子搜索列表
        List<ItemForESPost> itemForESPostsList = new ArrayList<>();

        for (BbsTPostMain bbsTPostMain : bbsTPostMainsList) {
            //查找发帖人的openId
            String openid = bbsTPostMain.getPMasterid();
            //查找对应的userInfo
            TUserinfo tUserinfo = tUserInfoRepository.findByCiId(openid);

            //封装ItemForESPost
            ItemForESPost itemForESPost = new ItemForESPost();

            //头像
            itemForESPost.setCiAvatarturl(tUserinfo.getCiAvatarturl());
            //昵称
            itemForESPost.setCiNkname(tUserinfo.getCiNkname());
            //发布日期
            itemForESPost.setCreateTime(bbsTPostMain.getCreateTime());
            //地点信息
            itemForESPost.setPLocation(bbsTPostMain.getPLocation());
            //标题
            itemForESPost.setPTitle(bbsTPostMain.getPTitle());
            //ID
            itemForESPost.setPId(bbsTPostMain.getPId());
            //图像路径
            itemForESPost.setImageUrl(bbsTPostMain.getPImage());

            itemForESPostsList.add(itemForESPost);
        }
        itemForESService.saveBatchForPost(itemForESPostsList);

    }


    @Test
    public void testSearch(){
        //高亮信息
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("knoCardTitle").preTags("<span style = 'color:red'>").postTags("</span>");

        //构建条件对象
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        //必填选项--必定查询
        List<QueryBuilder> must = new BoolQueryBuilder().must();
        must.add(QueryBuilders.matchQuery("knoCardTitle","抑郁"));

        //选填项--选择查询
        List<QueryBuilder> should = new BoolQueryBuilder().should();
        should.add(QueryBuilders.matchQuery("knoCardContent","抑郁"));

         NativeSearchQuery query = new NativeSearchQueryBuilder()
                 //条件
                .withQuery(queryBuilder)
                 //高亮
                .withHighlightBuilder(highlightBuilder)
                 //分页
                .withPageable(PageRequest.of(0,2)).build(); //一页2个

        //获取查询结果信息
        SearchHits<ItemForESKnowledge> search = elasticsearchRestTemplate.search(query, ItemForESKnowledge.class);
        List<ItemForESKnowledge> list = new ArrayList<>();
        for (SearchHit<ItemForESKnowledge> itemForESSearchHit : search) {
            ItemForESKnowledge itemForESKnowledge = itemForESSearchHit.getContent();
            //提前判断一下是否有高亮信息
            if(itemForESSearchHit.getHighlightFields().containsKey("knoCardTitle")){
                itemForESKnowledge.setKnoCardTitle(itemForESSearchHit.getHighlightFields().get("knoCardTitle").get(0));
            }
            list.add(itemForESKnowledge);
        }
        System.out.println(list);

        //判断是否还有多余的数据
        if(list.size()>(0+1)*2){
            System.out.println(true);
        }


    }

}