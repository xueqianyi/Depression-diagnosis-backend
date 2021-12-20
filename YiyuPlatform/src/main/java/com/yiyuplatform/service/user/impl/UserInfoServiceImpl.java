package com.yiyuplatform.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.config.Status.CustomErrors;
import com.yiyuplatform.entity.DiagnoseTRecords;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.repository.DiagnoseTRecordsRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.user.UserInfoService;
import com.yiyuplatform.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: Drew
 * @Date: 2021/10/28 11:29
 * UserInfoService接口实现
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private TUserInfoRepository tUserInfoRepository;
    @Autowired
    private DiagnoseTRecordsRepository tRecordsRepository;

//    @Override
//    @Transactional(rollbackOn = RuntimeException.class)
//    public List<UserInfoVO> findDataVO() {
    // 先查TUserInfo表
//        List<TUserinfo> tUserinfoList = tUserInfoRepository.findAll();
    // 转为VO 写法1
//        List<WechatAccountVO> wechatAccountVOList=new ArrayList<>();
//        for(TUserinfo tUserinfo : tUserinfoList){
//            WechatAccountVO wechatAccountVO=new WechatAccountVO();
//            wechatAccountVO.setCiId(tUserinfo.getCiId());
//            wechatAccountVO.setCiNkname(tUserinfo.getCiNkname());
//        }
//        写法2 使用stream流
//        List<UserInfoVO> userInfoVOList = tUserinfoList.stream()
//                .map(e -> new UserInfoVO(
//                        e.getCiId(),
//                        e.getCiNkname(),
//                        e.getCiPhonenumber(),
//                        e.getCiIdCard(),
//                        e.getCiAvatarturl(),
//                        e.getCiPersontype(),
//                        e.getCiLevel()
//                )).collect(Collectors.toList());
    // 装载到dataVO
//        return userInfoVOList;
//    }

    @Override
    @Transactional
    public Object RetrieveUser(String userId) throws RuntimeException {
        try {
            TUserinfo tUserinfo = tUserInfoRepository.findByCiId(userId);
            do {
                // 如果用户不存在
                if (tUserinfo == null) {
                    throw ExceptionManager.create(CustomErrors.UnknownUser.getErrorCode(),
                            CustomErrors.UnknownUser.getErrorMsg());
                }
                // 如果存在则返回
                UserInfoVO userInfoVO = new UserInfoVO(tUserinfo.getCiId(), tUserinfo.getCiNkname(),
                        tUserinfo.getCiPersontype(), tUserinfo.getCiRealname(), tUserinfo.getCiIdCard(),
                        tUserinfo.getCiSex(), tUserinfo.getCiPhonenumber(), tUserinfo.getCiAvatarturl(),
                        tUserinfo.getCiLevel(), tUserinfo.getCiStatus());
                return JSONObject.toJSON(userInfoVO);
            } while (false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public Object RetrieveDiagnose(String userId) {
        try {
            List<DiagnoseTRecords> records=tRecordsRepository.findByDiPatientId(userId);
            return JSONObject.toJSON(records);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
