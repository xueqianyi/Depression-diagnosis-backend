package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;

import com.yiyuplatform.entity.KnoInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



/**
 * @description: 文章详情测试类
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:54
 */

@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class KnoInfoRepositoryTest {

    @Autowired
    private KnoInfoRepository repository;

    @Test
    public void findAll(){
        List<KnoInfo> list = repository.findAll();
        for (KnoInfo knoInfo : list) {
            System.out.println(knoInfo);
        }
    }

    @Test
    public void findByKnoIshot(){
        List<KnoInfo> knoInfo = repository.findByKnoIshot(1);
        System.out.println(knoInfo);
    }

    @Test
    public void findByCategoryType(){
        List<KnoInfo> knoInfoList = repository.findByCategoryType(1);
        System.out.println(knoInfoList);
    }

    @Test
    public void findByKnoCardId(){
        KnoInfo knoInfo = repository.findByKnoCardId(1);
        System.out.println(knoInfo);
    }

}