package com.yiyuplatform.service.user;

import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.vo.UserInfoVO;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/19
 * \* Time: 22:44
 * \* Description: Customer业务接口，需要实现
 * \
 */
public interface UserInfoService {
//    List<UserInfoVO> findDataVO();
    /**
     * 查询用户信息
     * @param userId
     * @return java.lang.Object
     */
    Object RetrieveUser(String userId);
    /**
     * 用户诊断历史查询
     * @param userId
     * @return
     */
    Object RetrieveDiagnose(String userId);
}
