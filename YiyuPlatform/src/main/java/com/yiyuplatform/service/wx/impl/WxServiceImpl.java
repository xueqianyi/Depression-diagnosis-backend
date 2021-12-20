package com.yiyuplatform.service.wx.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.config.Status;
import com.yiyuplatform.config.WxConfig;
import com.yiyuplatform.entity.SessionTKey;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.form.WxLoginForm;
import com.yiyuplatform.repository.SessionTKeyRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.wx.WxService;
import com.yiyuplatform.util.DecodeUtil;
import com.yiyuplatform.util.RedisUtil;
import com.yiyuplatform.vo.WxLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/12
 * \* Time: 19:30
 * \* Description:
 * \
 */
@Service
@Slf4j
public class WxServiceImpl implements WxService {
    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private TUserInfoRepository tUserInfoRepository;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SessionTKeyRepository sessionRepo;


    @Override
    @Transactional
    /**
     * 用于处理用户登录业务
     * @param wxLoginForm 前端用户提交的表单
     * @return java.lang.Object
     */
    public Object UserLogin(WxLoginForm wxLoginForm) throws RuntimeException{
        try {
            // 获取用户session_key
            Map<String, Object> map = wxConfig.getSessionByCode(wxLoginForm.getCode());
            String token="";
            // 用户匿名标志
            boolean anoy=false;
            if (map.isEmpty()) {
                throw ExceptionManager.create(Status.CustomErrors.CodeIsEmpty.getErrorCode(), Status.CustomErrors.CodeIsEmpty.getErrorMsg());
            } else {
                String session_key = map.get("session_key").toString();
                String openid = map.get("openid").toString();
                log.info("==========================\n 获取到了openid"+openid);
                //Raw Data
                Map<String, Object> rawDataMap = JSON.parseObject(wxLoginForm.getRawData(), HashMap.class);
                String outSignature = DecodeUtil.getSha1(wxLoginForm.getRawData() + session_key);
                if (!wxLoginForm.getSignature().equals(outSignature)) {
                    throw ExceptionManager.create(Status.CustomErrors.SignatureError.getErrorCode(), Status.CustomErrors.SignatureError.getErrorMsg());
                }
                Map<String, Object> userInfo = wxConfig.getUserInfo(wxLoginForm.getEncrypteData(), session_key, wxLoginForm.getIv());
                if(userInfo.get("nickName").equals("微信用户"))
                    anoy=true;
                do{
                    TUserinfo pUser=tUserInfoRepository.findByCiId(openid);
                    JSONObject json=JSON.parseObject(JSON.toJSONString(userInfo));
                    JSONObject watermark=JSON.parseObject(JSON.toJSONString(json.get("watermark")));
                    String nickName=json.get("nickName").toString();
                    Integer gender=(Integer)json.get("gender");
                    String avatarUrl=json.get("avatarUrl").toString();
                    //用户微信数据时间戳
                    Integer timestamp=(Integer) watermark.get("timestamp");
                    // 判断新用户，写入数据库，并写入登录记录表
                    if(pUser==null){
                        TUserinfo insertUser=new TUserinfo();
                        insertUser.setCiId(openid);
                        insertUser.setCiNkname(nickName);
                        insertUser.setCiPersontype(1);
                        insertUser.setCiSex(gender);
                        // 匿名用户status为,0，非匿名用户status为1，详细说明见数据表说明
                        if(anoy) {
                            insertUser.setCiStatus(0);
                        }else{
                            insertUser.setCiStatus(1);
                        }
                        insertUser.setCiAvatarturl(avatarUrl);
                        insertUser.setCiLevel(1);
                        insertUser.setCiTimestamp(timestamp);
                        tUserInfoRepository.save(insertUser);
                        log.info("新用户登录，数据入库"+insertUser.getCiNkname());
                        break;
                    }
                    // 验证数据时效性，如果时效性过了就更新用户表里相关信息
                    if(pUser.getCiTimestamp()!=timestamp){
                        tUserInfoRepository.updateWxUserInfoByCiId(openid,nickName,gender,avatarUrl,timestamp);
                        break;
                    }
                }while (false);

                do{
                    SessionTKey pSkey=sessionRepo.findBySeUid(openid);
                    token=DecodeUtil.md5(openid,session_key);
                    // 如果session表里没有该用户的记录，则需要写入session表
                    if(pSkey==null){
                        SessionTKey sessionTKey=new SessionTKey();
                        sessionTKey.setSeSessionKey(session_key);
                        sessionTKey.setSeUid(openid);
                        sessionTKey.setSeSessionToken(token);
                        sessionRepo.save(sessionTKey);
                        log.info("session_key入库|"+sessionTKey);
                        break;
                    }
                    // 如果用户session_key变化，需要更新session_key和token
                    if(!pSkey.getSeSessionKey().equals(session_key)){
                        sessionRepo.updateSeSessionKeyAndSessionTokenBySeUid(openid,session_key,token);
                        break;
                    }
                }while(false);
                log.info("用户登录："+openid);
                JSONObject userInfoJson=JSON.parseObject(JSON.toJSONString(userInfo));
                SessionTKey newUserSession=sessionRepo.findBySeUid(openid);
                return JSONObject.toJSON(new WxLoginVO(token,openid,userInfoJson));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
