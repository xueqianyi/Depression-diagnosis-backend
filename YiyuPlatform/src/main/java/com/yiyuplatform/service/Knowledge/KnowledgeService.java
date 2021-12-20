package com.yiyuplatform.service.Knowledge;


import com.yiyuplatform.vo.Knowledge.HotKnowledgeVO;
import com.yiyuplatform.vo.Knowledge.KnowledgeDetailVO;

import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-11-24 22:00
 */
public interface KnowledgeService {
    //首页热门知识
    public List<HotKnowledgeVO> findHotKnowledgeVOByKnoIshot(Integer knoIshot);
    //首页更多知识列表
    public List<HotKnowledgeVO> findKnowledgeListByCategoryType(String categoryName);
    //点击进入的详细内容
    public KnowledgeDetailVO findKnowledgeDetailByID(Integer knoCardId);
}
