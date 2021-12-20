package com.yiyuplatform.service.authority.impl;

import com.yiyuplatform.config.Status;
import com.yiyuplatform.config.Status.CustomErrors;
import com.yiyuplatform.entity.SessionTKey;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.repository.SessionTKeyRepository;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.util.DecodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * \* Date: 2021/11/16
 * \* Time: 23:14
 * \* Description:
 * \
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    SessionTKeyRepository sessionRepo;


    @Override
    public void IsToKenRight(String token,String userId) throws RuntimeException {
        try {
            SessionTKey queryUser=sessionRepo.findBySeUid(userId);
            log.info("token"+token);
            log.info("1111"+queryUser.getSeSessionToken());
            // 检验token是否正确
            if(queryUser==null||!queryUser.getSeSessionToken().equals(token)){
                throw ExceptionManager.create(CustomErrors.TokenError.getErrorCode(),
                        CustomErrors.TokenError.getErrorMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String GetUserIdByToken(String token) {
        try {
            token= URLDecoder.decode(token,"UTF-8");
            SessionTKey sessionTKey=sessionRepo.findBySeSessionToken(token);
            if(sessionTKey==null){
                throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataNotExist.getErrorMsg());
            }
            String realSessionKey=sessionTKey.getSeSessionKey();
            String openid=sessionTKey.getSeUid();
            if(DecodeUtil.verify(openid,realSessionKey,token)){
                return openid;
            }else {
                throw ExceptionManager.create(Status.CustomErrors.TokenError.getErrorCode(),Status.CustomErrors.TokenError.getErrorMsg());
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        throw ExceptionManager.create(Status.CustomErrors.RequestError.getErrorMsg(), Status.CustomErrors.RequestError.getErrorMsg());
    }

    @Override
    public void CheckOpenId(String openId) {
        try {
            if(!StringUtils.hasText(openId)){
                throw ExceptionManager.create(Status.CustomErrors.TokenError.getErrorCode(),Status.CustomErrors.TokenError.getErrorMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
