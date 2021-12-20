package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.entity.KnoCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @description: 文章类别测试类
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:10
 */

@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class KnoCategoryRepositoryTest {

    @Autowired
    private KnoCategoryRepository repository;

    @Test
    public void findAll(){
        List<KnoCategory> list = repository.findAll();
        for (KnoCategory knoCategory : list) {
            System.out.println(knoCategory);
        }
    }

    @Test
    public void findByCategoryType(){
        KnoCategory knoCategory = repository.findByCategoryType(1);
        System.out.println(knoCategory.getCategoryName());
    }

    @Test
    public void findByCategoryName(){
        KnoCategory knoCategory = repository.findByCategoryName("生活");
        System.out.println(knoCategory);
    }


}