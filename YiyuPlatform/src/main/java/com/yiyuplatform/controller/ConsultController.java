package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSON;
import com.yiyuplatform.form.ConsultForm;
import com.yiyuplatform.form.ConsultMsgForm;
import com.yiyuplatform.form.diagnose.SubmitDiagnoseForm;
import com.yiyuplatform.service.consult.DoctorService;
import com.yiyuplatform.vo.ConsultHistoryVO;
import com.yiyuplatform.vo.DoctorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author liyuxuan
 * @Date 2021/11/22 21:59
 * @Description
 */

@RestController
@Api(tags = "咨询功能")
@RequestMapping("api")
public class ConsultController {


    @Autowired
    private DoctorService doctorService;
    private ConsultMsgForm consultMsgForm;
    @ApiOperation("提交医生信息")
    @GetMapping("consult/doctor_list")
    public String DoctorGet(@RequestParam Integer page) {
        DoctorVO doctorVO=new DoctorVO();
        /**
         * code here
         * */
        DoctorVO.FeedbackVO feedbackVO =doctorVO.new FeedbackVO();
        return JSON.toJSONString(feedbackVO);
    }
    @ApiOperation("提交咨询历史")
    @GetMapping("consult/history_retrieve")
    public String ConsultHistoryGet(@RequestBody SubmitDiagnoseForm submitDiagnose) {
        ConsultHistoryVO consultHistoryVO=new ConsultHistoryVO();
        /**
         * code here
         * */
        ConsultHistoryVO.FeedbackVO feedbackVO =consultHistoryVO.new FeedbackVO();
        return JSON.toJSONString(feedbackVO);
    }
    @ApiOperation("发起咨询")
    @PostMapping("consult/submit_consult")
    public String SubmitConsult(@RequestBody ConsultForm consultForm) {
        DoctorVO doctorVO=new DoctorVO();
        /**
         * code here
         * */
        DoctorVO.FeedbackVO feedbackVO =doctorVO.new FeedbackVO();
        return JSON.toJSONString(feedbackVO);
    }

    @ApiOperation("结束咨询")
    @PostMapping("consult/end_consult")
    public String EndConsult(@RequestBody ConsultForm consultForm) {
        DoctorVO doctorVO=new DoctorVO();
        /**
         * code here
         * */
        DoctorVO.FeedbackVO feedbackVO =doctorVO.new FeedbackVO();
        return JSON.toJSONString(feedbackVO);
    }

}


