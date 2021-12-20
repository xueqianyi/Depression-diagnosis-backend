package com.yiyuplatform.service.Knowledge.impl;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.service.Knowledge.KnowledgeService;
import com.yiyuplatform.vo.Knowledge.HotKnowledgeVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-11-25 1:41
 */

@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
class KnowledgeServiceImplTest {
    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    void findHotKnowledgeVOByKnoIshot() {
       List<HotKnowledgeVO> hotKnowledgeVOList = knowledgeService.findHotKnowledgeVOByKnoIshot(1);
    }

    @Test
    void findKnowledgeListByCategoryType(){
        List<HotKnowledgeVO> hotKnowledgeVOList = knowledgeService.findKnowledgeListByCategoryType("生活");
    }

}