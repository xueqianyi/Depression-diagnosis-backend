package com.yiyuplatform.repository;

import com.yiyuplatform.entity.UserTLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/28
 * \* Time: 8:12
 * \* Description: UserLog接口类
 * \
 */
public interface UserLogRepository extends JpaRepository<UserTLog, Integer> {
}
