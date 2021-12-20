package com.yiyuplatform.service.schedule;

/**
 * \* Date: 2021/11/17
 * \* Time: 21:44
 * \* Description:
 * \
 */
public interface ScheduleService {
    void getUserAccessToken();
    void GetReadNum();
    void ClearMessageLine();

    //知识模块知识模块
    void GetReadNumForKnowledge();
}
