package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.entity.TDoctor;
import com.yiyuplatform.entity.TUserinfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-05 17:27
 */
@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
class TUserInfoRepositoryTest {
    @Autowired
    private TUserInfoRepository tUserInfoRepository;
    @Test
    public void findByCiId() {
        TUserinfo tUserInfo = tUserInfoRepository.findByCiId("1");
        System.out.println(tUserInfo);
    }

}