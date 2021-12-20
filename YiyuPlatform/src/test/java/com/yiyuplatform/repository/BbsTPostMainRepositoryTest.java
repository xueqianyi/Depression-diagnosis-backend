package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.config.RedisConfig;
import com.yiyuplatform.util.RedisUtil;
import com.yiyuplatform.vo.bbs.BbsPostCommentVO;
import com.yiyuplatform.vo.bbs.BbsPostMainVO;
import lombok.var;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
class BbsTPostMainRepositoryTest {
    @Autowired
    BbsTPostMainRepository bT;
    @Autowired
    BbsTPostSubRepository bsT;
    @Autowired
    RedisUtil redisUtil;
    @Test
    @Transactional
    void getPostListByPage() {
        bT.UpdateReadNum("1",100);
    }


    /**
     * 帖子按照时间排序测试
     */
    @Test
    void sortTest(){
        var allUsersSort=bT.findAll(Sort.by(Sort.Direction.DESC,"updateTime"));
        System.out.println(allUsersSort);
    }
}