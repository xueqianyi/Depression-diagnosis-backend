package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.exception.CustomException;
import com.yiyuplatform.form.UserHealthyRecord.HealthyForm;
import com.yiyuplatform.service.UserHealthyRecord.UserHealthyRecordService;
import com.yiyuplatform.service.authority.AuthorityService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @description: 用户信息表类Controller
 * @author: Xue Qianyi
 * @date: 2021-12-02 9:05
 */


@RestController
@RequestMapping("healthy")
@Slf4j
public class UserHealthyRecordController {

    @Autowired
    private UserHealthyRecordService userHealthyRecordService;
    @Autowired
    private AuthorityService authorityService;


    /**
     * 获取某用户的健康信息表
     * @param token
     * @param ciId
     * @return
     */
    @ApiOperation("获取某用户的健康信息表")
    @GetMapping("/get_healthy_record")
    public Object getRecordByCiId(@RequestHeader("token") String token, @RequestParam(value = "ciId",required = true)String ciId){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return JSONObject.toJSON(userHealthyRecordService.UserHealthyRecordByCiId(ciId));
    }


    /**
     * 修改用户的健康信息表（更新/插入）
     * @param token
     * @param healthyForm
     */
    @ApiOperation("修改用户的健康信息表（更新/插入）")
    @RequestMapping(method = RequestMethod.POST,value = "/post_healthy_record",consumes ="application/json;charset=UTF-8") //,produces = "application/json;charset=UTF-8"
    @ResponseBody
    public void postHealthyRecord(@RequestHeader("token") String token,@RequestBody HealthyForm healthyForm){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("用户开始修改健康信息表");
        log.info("healthyForm"+healthyForm);
        String ciId = healthyForm.getCiId();
        String relation = healthyForm.getRelation();
        Integer sex = healthyForm.getSex();
        String userName = healthyForm.getUserName();
        Date userBirthday = Date.valueOf(healthyForm.getUserBirthday());
        BigDecimal userHeight = new BigDecimal(healthyForm.getUserHeight());
        BigDecimal userWeight = new BigDecimal(healthyForm.getUserWeight());
        BigDecimal userBmi = new BigDecimal(healthyForm.getUserBmi());
        String diseaseHistory = healthyForm.getDiseaseHistory();
        String smokeHistory = healthyForm.getSmokeHistory();

        //如果用户已经插入了，则更新
        if(userHealthyRecordService.UserHealthyRecordByCiId(ciId) == null){
            //插入新的健康信息表
            userHealthyRecordService.InsertHealthyRecord(relation,userName,sex,userBirthday,userHeight,userWeight,userBmi,diseaseHistory,smokeHistory,ciId);

        }else{
            //更新健康信息表
            userHealthyRecordService.UpdateUserHealthyRecord(relation,userName,sex,userBirthday,userHeight,userWeight,userBmi,diseaseHistory,smokeHistory,ciId);
        }
    }

    /**
     * 获取某用户的健康信息表
     * @param token
     * @param ciId
     */
    @ApiOperation("获取某用户的健康信息表")
    @GetMapping("/get_healthy_record/delete")
    public void deleteByOpenId(@RequestHeader("token") String token,@RequestParam(value = "ciId",required = true)String ciId){
        log.info("开始获取某用户的健康信息表");
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        if(userHealthyRecordService.UserHealthyRecordByCiId(ciId) == null){
            throw new CustomException("1004","用户输入的OpenId查询对应的健康信息表为空");
        }else{
            userHealthyRecordService.DeleteByCiId(ciId);
        }
    }

    /**
     * 判断某用户是否填写健康信息表
     * @param token
     * @param ciId
     * @return
     */
    @ApiOperation("判断某用户是否填写健康信息表")
    @GetMapping("/get_healthy_record/isfinish")
    public boolean isFillTable(@RequestHeader("token") String token,@RequestParam(value = "openid",required = true)String ciId){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("开始判断某用户是否填写健康信息表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return (boolean) JSONObject.toJSON(userHealthyRecordService.isFillTable(ciId));
    }
}
