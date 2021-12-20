package com.yiyuplatform.service.Knowledge.impl;

import com.yiyuplatform.config.RedisConfig;
import com.yiyuplatform.config.Status;
import com.yiyuplatform.entity.KnoCategory;
import com.yiyuplatform.entity.KnoInfo;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.repository.KnoCategoryRepository;
import com.yiyuplatform.repository.KnoInfoRepository;
import com.yiyuplatform.service.Knowledge.KnowledgeService;
import com.yiyuplatform.util.RedisUtil;
import com.yiyuplatform.vo.Knowledge.HotKnowledgeVO;
import com.yiyuplatform.vo.Knowledge.KnowledgeDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @description: 知识模块service层
 * @author: Xue Qianyi
 * @date: 2021-11-24 22:02
 */
@Slf4j
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnoInfoRepository knoInfoRepository;
    @Autowired
    private KnoCategoryRepository knoCategoryRepository;
    @Autowired
    RedisUtil redisUtil;

    //首页的热门列表
    @Override
    @Transactional
    public List<HotKnowledgeVO> findHotKnowledgeVOByKnoIshot(Integer knoIshot) {
        //详情信息
        List<KnoInfo> knoInfoList = knoInfoRepository.findByKnoIshot(knoIshot);
        List<HotKnowledgeVO> hotKnowledgeVOList = new ArrayList<>();

        for (KnoInfo knoInfo : knoInfoList) {
            HotKnowledgeVO hotKnowledgeVO = new HotKnowledgeVO();
            //类别编号
            Integer type = knoInfo.getCategoryType();
            //类别信息
            KnoCategory knoCategory = knoCategoryRepository.findByCategoryType(type);

            //装载数据
            //ID"knowledgeId"
            hotKnowledgeVO.setKnoCardId(knoInfo.getKnoCardId());
            //标题"title"
            hotKnowledgeVO.setKnoCardTitle(knoInfo.getKnoCardTitle());
            //类别"category"
            hotKnowledgeVO.setCategoryName(knoCategory.getCategoryName());
            //发布日期"releaseDate"
            hotKnowledgeVO.setKnoCardReleaseDate(knoInfo.getKnoCardReleaseDate());
            //封面小图"coverImageUrl"
            hotKnowledgeVO.setKnoCardIcon(knoInfo.getKnoCardIcon());
            //阅读量"readAmount"
            hotKnowledgeVO.setKnoCardRead(knoInfo.getKnoCardRead());
            //点赞数"likes"
            hotKnowledgeVO.setKnoCardLikes(knoInfo.getKnoCardLikes());

            hotKnowledgeVOList.add(hotKnowledgeVO);
        }

        return hotKnowledgeVOList;
    }

    //首页更多的知识列表
    @Override
    @Transactional
    public List<HotKnowledgeVO> findKnowledgeListByCategoryType(String categoryName) {
        //获取类别名称对应的type
        KnoCategory knoCategory = knoCategoryRepository.findByCategoryName(categoryName);
        //找到type
        Integer type = knoCategory.getCategoryType();
        //获取列表
        List<KnoInfo> knoInfoList = knoInfoRepository.findByCategoryType(type);

        List<HotKnowledgeVO> hotKnowledgeVOList = new ArrayList<>();

        for (KnoInfo knoInfo : knoInfoList) {
            HotKnowledgeVO hotKnowledgeVO = new HotKnowledgeVO();

            hotKnowledgeVO.setKnoCardId(knoInfo.getKnoCardId());
            hotKnowledgeVO.setKnoCardTitle(knoInfo.getKnoCardTitle());
            hotKnowledgeVO.setKnoCardIcon(knoInfo.getKnoCardIcon());
            hotKnowledgeVO.setCategoryName(categoryName);
            hotKnowledgeVO.setKnoCardReleaseDate(knoInfo.getKnoCardReleaseDate());
            hotKnowledgeVO.setKnoCardRead(knoInfo.getKnoCardRead());
            hotKnowledgeVO.setKnoCardLikes(knoInfo.getKnoCardLikes());

            hotKnowledgeVOList.add(hotKnowledgeVO);
        }
        return hotKnowledgeVOList;
    }

    //点击进入的详情
    @Override
    @Transactional
    public KnowledgeDetailVO findKnowledgeDetailByID(Integer knoCardId) {
        try {
            KnoInfo knoInfo = knoInfoRepository.findByKnoCardId(knoCardId);

            KnowledgeDetailVO knowledgeDetailVO = new KnowledgeDetailVO();

            if (knowledgeDetailVO == null) {
                throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataNotExist.getErrorMsg());
            }

            String postRedisKey= RedisConfig.KNOWLEDGE_READ_NUM;
            //内存中没有该项，则先创建
            String id = knoCardId.toString();
            if(!redisUtil.hHasKey(postRedisKey,id)){
                redisUtil.hset(postRedisKey,id,0);
            }
            redisUtil.hincr(postRedisKey,id,1);

            //ID
            knowledgeDetailVO.setKnoCardId(knoInfo.getKnoCardId());
            //知识卡作者
            knowledgeDetailVO.setKnoCardAuthor(knoInfo.getKnoCardAuthor());
            //知识卡点赞数
            knowledgeDetailVO.setKnoCardLikes(knoInfo.getKnoCardLikes());
            //知识卡浏览量
            knowledgeDetailVO.setKnoCardRead(knoInfo.getKnoCardRead());
            //发布日期
            knowledgeDetailVO.setKnoCardReleaseDate(knoInfo.getKnoCardReleaseDate());
            //知识文章标题
            knowledgeDetailVO.setKnoCardTitle(knoInfo.getKnoCardTitle());
            //文章类型
            knowledgeDetailVO.setCategoryType(knoInfo.getCategoryType());
            //是否是热门
            knowledgeDetailVO.setKnoIshot(knoInfo.getKnoIshot());
            //文章正文
            knowledgeDetailVO.setKnoCardContent(knoInfo.getKnoCardContent());
            //文章摘要
            knowledgeDetailVO.setKnoCardSummary(knoInfo.getKnoCardSummary());
            //文章图片
            knowledgeDetailVO.setKnoCardIcon(knoInfo.getKnoCardIcon());
            //分类ID
            knowledgeDetailVO.setCategoryId(knoInfo.getKnoCardId());

            return knowledgeDetailVO;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


}
