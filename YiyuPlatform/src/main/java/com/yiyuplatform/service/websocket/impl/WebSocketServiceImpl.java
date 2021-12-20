package com.yiyuplatform.service.websocket.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.DataListener;
import com.yiyuplatform.config.NettySocketConfig;
import com.yiyuplatform.config.NettySocketProperties;
import com.yiyuplatform.config.RedisConfig;
import com.yiyuplatform.entity.ChatTRecords;
import com.yiyuplatform.entity.SessionTKey;
import com.yiyuplatform.form.ConsultMsgForm;
import com.yiyuplatform.repository.ChatTRecordsRepository;
import com.yiyuplatform.repository.SessionTKeyRepository;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.websocket.WebSocketService;
import com.yiyuplatform.util.RedisUtil;
import com.yiyuplatform.vo.socketobj.CacheMessageVO;
import com.yiyuplatform.vo.socketobj.ChatMessageVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/6
 * \* Time: 20:10
 * \* Description:
 * \
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties({
        NettySocketProperties.class,
})
public class WebSocketServiceImpl implements WebSocketService {
    //用户池
    private static final Map<UUID, SocketIOClient> CLIENT_MAP = new ConcurrentHashMap<>();
    private final SocketIOServer socketIOServer;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SessionTKeyRepository sessionRepo;
    @Autowired
    private ChatTRecordsRepository chatTRecordsRepository;
    @Autowired
    private AuthorityService authorityService;

    /**
     *  服务器启动时会调用一次
     */
    @PostConstruct
    @Override
    public void autoStart() {
        log.info("WebWocket服务器启动");
        // 建立连接监听事件 - Token校验
        socketIOServer.addConnectListener(client -> {
            System.out.println(client.toString());
            String token = getClientKey(client, "token");
            log.info("token"+token);
            String openid=authorityService.GetUserIdByToken(token);
            if (StringUtils.hasText(openid)) {
                log.info("check success");
                log.info("新用户："+client.getSessionId());
                CLIENT_MAP.put(client.getSessionId(), client);
                redisUtil.hset(RedisConfig.redisTokenKey,openid,client.getSessionId().toString());
//                redisUtil.hset(RedisConfig.redisTokenKey,token,client.getSessionId().toString());
            } else {
                client.disconnect();
            }
        });



        // 断开连接监听事件 - token移除
        socketIOServer.addDisconnectListener(client -> {
            CLIENT_MAP.remove(client.getSessionId());
            String token = getClientKey(client, "token");
            client.disconnect();
            redisUtil.hdel(RedisConfig.redisTokenKey,token,client.getSessionId().toString());
            log.info("移除client:{}", client.getSessionId());
        });
        socketIOServer.addEventListener("chat", ChatMessageVO.class, new DataListener<ChatMessageVO>() {
            @Override
            public void onData(SocketIOClient client, ChatMessageVO data, AckRequest ackSender) throws Exception {
                log.info("收到聊天信息");
                Map<String,Object> map=JSON.parseObject(JSON.toJSONString(data));
                if(sendMsg(map ,data.getReceiverId(),"chat")==1){
                    // 如果没有发送成功，则通知用户对方不在线
                    JSONObject json=new JSONObject();
                    json.put("msg","对方不在线");
                    client.sendEvent("user_offline",json);
                }else{
                    // 向聊天记录表里写入记录
                    ChatTRecords newRecord=new ChatTRecords();
                    newRecord.setMId(String.valueOf(UUID.randomUUID()));
                    newRecord.setMSenderid(data.getSenderId());
                    newRecord.setMStatus(0);
                    newRecord.setMSessionkey(data.getSessionKey());
                    newRecord.setMType(1);
                }
            }
        });
        // 添加其他监听事件
        socketIOServer.start();
        log.info("start finish");
    }


    @Override
    /**
     *  获取Header里的Token
     * @Param: client
     * @Param: key
     * @return: java.lang.String
     */
    public String getClientKey(SocketIOClient client, String key) {
//        HttpHeaders httpHeaders = client.getHandshakeData().getHttpHeaders();
//        return httpHeaders.get(key);
        return client.getHandshakeData().getSingleUrlParam(key);

    }

    @PreDestroy
    @Override
    public void onDestroy() {
        if (socketIOServer != null) {
            socketIOServer.stop();
        }
    }

    @Override
    public int sendMsg(Object demo) {
        /**
         * code here
         * */
        CLIENT_MAP.forEach((key, value) -> {
            value.sendEvent("server_event", demo);
            log.info("发送数据成功:{}", key);
        });
        return CLIENT_MAP.size();
    }

    @Override
    public int sendAll(Object demo) {
        try{
            socketIOServer.getBroadcastOperations();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int sendMsg(Map<String,Object> demo, String receiverId,String event) {
        SessionTKey receiverSession=sessionRepo.findBySeUid(receiverId);
        if(redisUtil.hasKey(RedisConfig.redisTokenKey)){
            if(redisUtil.hHasKey(RedisConfig.redisTokenKey,receiverId)){
                String key=String.valueOf(redisUtil.hget(RedisConfig.redisTokenKey,receiverId));
                UUID socketSessionKey=UUID.fromString(key);
                CLIENT_MAP.get(socketSessionKey).sendEvent(event,demo);
                log.info("发送数据成功:{}", socketSessionKey);
                return 0;
            }else{
                //如果用户不在线 则存入消息队列
                CacheMessageVO cacheMessageVO=new CacheMessageVO(event,receiverId,demo);
                redisUtil.lSet(RedisConfig.messageLineKey, JSON.toJSONString(cacheMessageVO));
            }
        }
        return 1;
    }



    @Override
    public boolean IsUserOnline(String receiverId) {
        return redisUtil.hHasKey(RedisConfig.redisTokenKey,receiverId);
    }

}
