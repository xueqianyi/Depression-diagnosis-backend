package com.yiyuplatform.service.consult.impl;

import com.alibaba.fastjson.JSON;
import com.yiyuplatform.config.Status;
import com.yiyuplatform.entity.ChatTContact;
import com.yiyuplatform.entity.ChatTRecords;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.repository.ChatTContactRepository;
import com.yiyuplatform.repository.ChatTRecordsRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.consult.ChatService;
import com.yiyuplatform.util.DecodeUtil;
import com.yiyuplatform.util.RandomUtil;
import com.yiyuplatform.vo.ChatListVO;
import com.yiyuplatform.vo.ChatSessionVO;
import com.yiyuplatform.vo.MessageVO;
import com.yiyuplatform.vo.socketobj.ChatMessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Autowired
    private TUserInfoRepository userRepo;
    @Autowired
    private ChatTContactRepository contactRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private ChatTRecordsRepository recordsRepository;
    @Override
    @Transactional
    public Object CreateConsult(String friendId, String userId) {
        try {
            String sessionKey= UUID.randomUUID().toString();
            ChatTContact chat=contactRepository.GetByChatSessionkey(sessionKey,userId);
            if(chat==null){
                ChatTContact newChat=new ChatTContact();
                newChat.setChatSessionkey(sessionKey);
                newChat.setChatMeId(userId);
                newChat.setChatFrdId(friendId);
                contactRepository.save(newChat);
                // 手动提交事务
                return QuerySession(sessionKey,userId);
            }else{
                return QuerySession(sessionKey,userId);
            }
        }catch (Exception e){
            transactionManager.rollback(TransactionAspectSupport.currentTransactionStatus());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object QuerySession(String sessionKey, String userId) {
        try {
            ChatTContact contact= contactRepository.GetByChatSessionkey(sessionKey,userId);
            if(contact==null){
                throw ExceptionManager.create(Status.CustomErrors.UnknownUser.getErrorCode(), Status.CustomErrors.UnknownUser.getErrorMsg());
            }
            String id;
            if(userId.equals(contact.getChatFrdId())){
                id=contact.getChatMeId();
            }else{
                id=contact.getChatFrdId();
            }
            TUserinfo friend=userRepo.findByCiId(id);
            if(friend==null){
                throw ExceptionManager.create(Status.CustomErrors.UnknownUser.getErrorCode(), Status.CustomErrors.UnknownUser.getErrorMsg());
            }
            List<ChatTRecords> RawMessageList=recordsRepository.findBymSessionkeyOrderBymSendtime(sessionKey);
            if(RawMessageList==null){
                return JSON.toJSON(new ChatSessionVO(friend.getCiId(),
                        friend.getCiNkname(),
                        friend.getCiAvatarturl()));
            }else{
                List<MessageVO> messageVOList=new ArrayList<>();
                for(ChatTRecords chatTRecords:RawMessageList){
                    messageVOList.add(new MessageVO(chatTRecords.getMSendtime(),chatTRecords.getMText(),chatTRecords.getMSenderid(),userId));
                }
                return JSON.toJSON(new ChatSessionVO(friend.getCiId(),
                        friend.getCiNkname(),
                        friend.getCiAvatarturl(),
                        messageVOList));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object QueryList(String openId) {
        try {
            List<ChatListVO> chatList=new ArrayList<>();
            chatList=contactRepository.GetChatList(openId);
            for(ChatListVO chatListVO:chatList){
                if(chatListVO.getId1().equals(openId)){
                    chatListVO.setFriendId(chatListVO.getId2());
                }else{
                    chatListVO.setFriendId(chatListVO.getId1());
                }
                TUserinfo friend=userRepo.findByCiId(chatListVO.getFriendId());
                if(friend!=null){
                    chatListVO.setFriendAvatar(friend.getCiAvatarturl());
                    chatListVO.setFriendName(friend.getCiNkname());
                }
            }
            return JSON.toJSON(chatList);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
