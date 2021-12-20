package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.entity.UserHealthyRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;


/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-02 1:21
 */
@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserHealthyRecordRepositoryTest {
    @Autowired
    private UserHealthyRecordRepository repository;
    @Autowired
    private TUserInfoRepository userRepo;
    @Autowired
    private ChatTRecordsRepository repo;

    @Test
    public void findAll() {
//        System.out.println(userRepo.GetChatUserInfo("ocu9t5MdG5DHl7XUx4Z6VyfWugY0")));
    }

    @Test
    public void findByCiId() {
        UserHealthyRecord userHealthyRecord = repository.findByCiId("122");
        System.out.println(userHealthyRecord);

    }

    @Test
    public void deleteById(){
        repository.deleteById("2");
    }

    @Test
    public void testLocalHost(){
        System.getProperties().list(System.out);
    }
}