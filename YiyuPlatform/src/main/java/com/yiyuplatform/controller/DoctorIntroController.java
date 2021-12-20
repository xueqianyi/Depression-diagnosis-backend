package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.service.Doctor.DoctorFirstPageService;
import com.yiyuplatform.service.authority.AuthorityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 医生列表首页controller
 * @author: Xue Qianyi
 * @date: 2021-12-02 23:03
 */
@RestController
@RequestMapping("/doctor_first_page")
public class DoctorIntroController {
    @Autowired
    private DoctorFirstPageService doctorFirstPageService;
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation("获取首页医生信息列表")
    @GetMapping("/list")
    public Object getFirstPageDoctorList(){
        //@RequestHeader String token
//        String openid=authorityService.GetUserIdByToken(token);
//        authorityService.CheckOpenId(openid);
        return JSONObject.toJSON(doctorFirstPageService.findDoctorList());
    }

    @ApiOperation("获取首页医生的详情信息")
    @GetMapping("/list/detail")
    public Object getDoctorDetailByDocId(@RequestParam(value = "openId",required = true)String docId){
        return JSONObject.toJSON(doctorFirstPageService.findDetailByDocId(docId));
    }



}
