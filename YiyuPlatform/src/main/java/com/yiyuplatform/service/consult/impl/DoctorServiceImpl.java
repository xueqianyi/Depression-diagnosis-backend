package com.yiyuplatform.service.consult.impl;

import com.alibaba.fastjson.JSON;
import com.yiyuplatform.repository.TDoctorRepository;
import com.yiyuplatform.service.consult.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author liyuxuan
 * @Date 2021/11/23 8:40
 * @Description
 */

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private TDoctorRepository onlineTDoctorRepository;
//    @Autowired
//    private DoctorRepository OnlineDoctorRepository;
    @Override
    public String findAll(){

        return JSON.toJSONString(onlineTDoctorRepository.findAll());
    }



}
