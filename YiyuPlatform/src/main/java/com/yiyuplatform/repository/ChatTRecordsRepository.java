package com.yiyuplatform.repository;

import com.yiyuplatform.entity.ChatTRecords;
import com.yiyuplatform.vo.MessageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/28
 * \* Time: 8:12
 * \* Description: ChatRecords接口类
 * \
 */
public interface ChatTRecordsRepository extends JpaRepository<ChatTRecords, Integer> {
    @Query("select record from ChatTRecords record where record.mSessionkey=?1")
    List<ChatTRecords> findBymSessionkeyOrderBymSendtime(String sessionKey);
}
