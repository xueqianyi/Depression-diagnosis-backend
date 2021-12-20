package com.yiyuplatform.service.schedule.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.yiyuplatform.config.RedisConfig;
import com.yiyuplatform.config.WxConfig;
import com.yiyuplatform.entity.BbsTPostMain;
import com.yiyuplatform.repository.BbsTPostMainRepository;
import com.yiyuplatform.repository.KnoInfoRepository;
import com.yiyuplatform.service.schedule.ScheduleService;
import com.yiyuplatform.service.websocket.WebSocketService;
import com.yiyuplatform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * \* Date: 2021/11/17
 * \* Time: 20:58
 * \* Description: 定时任务实现类
 * \
 */
@Slf4j
@Service
@Component
@EnableScheduling
@EnableAsync
public class ScheduleServiceImpl implements ScheduleService {
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.grantType}")
    private String grantType;
    @Value("${wxToken.url}")
    private String tokenUrl;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BbsTPostMainRepository bbsMainRepo;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private KnoInfoRepository knoInfoRepository;

    /**
     *  定时获取全局唯一accessToken, 调用微信后台API时需携带此access_token，服务器运行前调用一次
     * @Param:
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostConstruct
    @Async
    @Scheduled(cron = "0 0 */2 * * ?")
    @Override
    public void getUserAccessToken() {
        Map<String, Object> json = null;
        try {
            String accessTokenUrl = tokenUrl + "?" + "grant_type=" + grantType + "&appid=" + appId + "&secret=" + appSecret;
            // 发送请求
            String response = HttpRequest.get(accessTokenUrl).body();
            System.out.println(response);
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.readValue(response, Map.class);
            WxConfig.access_token = json.get("access_token").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新论坛帖子浏览量定时任务
     * @param
     * @return void
     */
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    @Override
    public void GetReadNum() {
        try {
            if(redisUtil.hasKey(RedisConfig.redisReadNumKey)) {
                String redisKey = RedisConfig.redisReadNumKey;
                Map<String, Object> pMap = JSON.parseObject(JSON.toJSONString(redisUtil.hmget(redisKey)));
                for (Map.Entry<String, Object> entry : pMap.entrySet()) {
                    bbsMainRepo.UpdateReadNum(entry.getKey(), ( Integer) entry.getValue());
                    redisUtil.hdel(redisKey, entry.getKey());
                }
            }
        }catch (Exception e){
            log.info("浏览量更新失败");
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 清理消息队列定时任务
     * @param
     * @return void
     */
    @Override
    @Async
    @Scheduled(cron = "*/20 * * * * ?")
    public void ClearMessageLine() {
        try{
            if(redisUtil.hasKey(RedisConfig.messageLineKey)) {
                log.info("处理消息队列");
                //获取缓存消息列表
                List<Object> messageList = redisUtil.lGet(RedisConfig.messageLineKey,0,redisUtil.lGetListSize(RedisConfig.messageLineKey));
                for (Object messageobj : messageList) {
                    Map<String,Object> messageBody=JSONObject.parseObject(String.valueOf(messageobj));
                    // 如果用户在线了，就发送此条消息，并删除
                    if (webSocketService.IsUserOnline(String.valueOf(messageBody.get("receiverId")))) {
                        //解析发送事件
                        String event=String.valueOf(messageBody.get("event"));
                        Map<String,Object> body=JSONObject.parseObject(JSON.toJSONString(messageBody.get("body")));
                        String receiverId=String.valueOf(messageBody.get("receiverId"));
                        if(webSocketService.sendMsg(body,receiverId,event)==0){
                            //检验空指针
                            // 发送成功，则删除该缓存消息
                            redisUtil.del(RedisConfig.messageLineKey);
                            log.info("缓存消息发送成功"+messageBody.toString());
                        }else{
                            log.info("缓存消息发送失败");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * 更新知识模块浏览量定时任务
     * @param
     * @return void
     */

    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    @Override
    public void GetReadNumForKnowledge() {
        try {
            if(redisUtil.hasKey(RedisConfig.redisReadNumKey)) {
                String redisKey= RedisConfig.KNOWLEDGE_READ_NUM;
                Map<String,Object> pMap= JSON.parseObject(JSON.toJSONString(redisUtil.hmget(redisKey)));
                for(Map.Entry<String,Object> entry:pMap.entrySet()){
                    Integer id = Integer.valueOf(entry.getKey());
                    knoInfoRepository.UpdateReadNumKnowledge(id,(Integer) entry.getValue());
                    redisUtil.hdel(redisKey,entry.getKey());
                }
            }
        }catch (Exception e){
            log.info("浏览量更新失败");
            e.printStackTrace();
            throw e;
        }
    }
}
