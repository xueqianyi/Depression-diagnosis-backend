package com.yiyuplatform.repository;

import com.yiyuplatform.YiyuPlatformApplication;
import com.yiyuplatform.entity.BbsTComments;
import com.yiyuplatform.service.consult.ChatService;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-07 20:36
 */
@ContextConfiguration(classes = YiyuPlatformApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class BbsTCommentsRepositoryTest {

    @Autowired
    public BbsTCommentsRepository bbsTCommentsRepository;
    @Autowired
    private ChatService chatService;
    @Test
    public void findByCoPostId(){
        chatService.QueryList("1");
    }

    @Test
    public void dateTransform(){
        List<BbsTComments> bbsTCommentsList = bbsTCommentsRepository.findByCoPostId("081834d5-c3d1-428e-855a-5bd2af24b329");
        for (BbsTComments bbsTComments : bbsTCommentsList) {
            Date dd = bbsTComments.getCoTime();
            String ddd = dd.toString();
            System.out.println(bbsTComments);
        }
    }
}