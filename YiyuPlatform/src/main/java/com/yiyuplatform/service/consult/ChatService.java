package com.yiyuplatform.service.consult;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/8
 * \* Time: 14:17
 * \* Description:
 * \
 */
public interface ChatService {
    Object CreateConsult(String friendId,String userId);
    /**
     * 查询一个聊天
     * @param sessionKey
     * @param userId
     * @return java.lang.Object
     */
    Object QuerySession(String sessionKey,String userId);
    Object QueryList(String openId);
}
