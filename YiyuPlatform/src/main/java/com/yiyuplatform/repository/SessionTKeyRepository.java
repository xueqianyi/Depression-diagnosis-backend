package com.yiyuplatform.repository;

import com.yiyuplatform.entity.SessionTKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/8
 * \* Time: 22:50
 * \* Description:
 * \
 */
public interface SessionTKeyRepository extends JpaRepository<SessionTKey, Integer> {
    /**
     *  根据OpenId(Uid)查找记录
     */
    @Query("select session from SessionTKey session where session.seUid=?1")
    SessionTKey findBySeUid(String openid);

    /**
     * 根据token查找记录
     * @param token
     * @return com.yiyuplatform.entity.SessionTKey
     */
    @Query("select session from SessionTKey session where session.seSessionToken=?1")
    SessionTKey findBySeSessionToken(String token);
    /**
     * 根据openid(uid)更新sessionkey和sessiontoken(服务器自定义登录态)
     * @param openid
     * @param sessionKey
     * @param sessionToken
     * @return void
     */
    @Modifying(clearAutomatically = true)
    @Query("update SessionTKey se set se.seSessionKey=?2,se.seSessionToken=?3 where se.seUid=?1")
    @Transactional
    void updateSeSessionKeyAndSessionTokenBySeUid(String openid,String sessionKey,String sessionToken);
}
