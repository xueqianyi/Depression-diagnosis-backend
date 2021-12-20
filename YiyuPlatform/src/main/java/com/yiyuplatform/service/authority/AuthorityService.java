package com.yiyuplatform.service.authority;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/8
 * \* Time: 14:19
 * \* Description:
 * \
 */
public interface AuthorityService {
    /**
     * 检查用户登录态是否正确
     * @param token
     * @return boolean
     */
    void IsToKenRight(String token,String userId);
    String GetUserIdByToken(String token);
    void CheckOpenId(String openId);
}
