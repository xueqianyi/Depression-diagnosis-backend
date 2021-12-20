package com.yiyuplatform.repository;

import com.yiyuplatform.entity.ChatTContact;
import com.yiyuplatform.vo.ChatListVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/28
 * \* Time: 8:12
 * \* Description: ChatContact接口类
 * \
 */
public interface ChatTContactRepository extends JpaRepository<ChatTContact, Integer> {
    @Query("select chat from ChatTContact chat where chat.chatSessionkey=?1 and (chat.chatMeId=?2 or chat.chatFrdId=?2)")
    ChatTContact GetByChatSessionkey(String sessionKey,String openid);

    @Query("select new com.yiyuplatform.vo.ChatListVO(contact.chatMeId,contact.chatFrdId,contact.chatSessionkey,contact.chatLastMessage,contact.chatLastTime)"+
    "from ChatTContact contact where contact.chatMeId=?1 or contact.chatFrdId=?1")
    List<ChatListVO> GetChatList(String userId);


}
