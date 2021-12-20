package com.yiyuplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.form.diagnose.ProgressCheckForm;
import com.yiyuplatform.form.diagnose.SubmitDiagnoseForm;
import com.yiyuplatform.form.diagnose.SubmitSuggestionForm;
import com.yiyuplatform.form.diagnose.UpdateFileInfo;
import com.yiyuplatform.service.authority.AuthorityService;
import com.yiyuplatform.service.diagnose.DiagnoseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Xue Qianyi, Drew
 * @Date: 8:38
 *  诊断controller
 */
@RestController
@Slf4j
@RequestMapping("/api/diagnose")
public class DiagnoseController {
    @Autowired
    private DiagnoseService diagnoseService;
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation("提交诊断表单zhl")
    @PostMapping(value = "/submit_diagnose")
    public Object SubmitDiagnose(@RequestHeader String token,@RequestBody SubmitDiagnoseForm submitDiagnoseForm) throws Exception{
        try {
            String openid=authorityService.GetUserIdByToken(token);
            authorityService.CheckOpenId(openid);
            return diagnoseService.SubmitDiagnose(submitDiagnoseForm);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @ApiOperation("诊断进度查询zhl")
    @PostMapping(value = "/process_check")
    public Object ProgressCheck(@RequestHeader String token, @RequestBody ProgressCheckForm progressCheckForm) {
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return diagnoseService.ProgressCheck(progressCheckForm.getUserId(), progressCheckForm.getFormId());
    }

    @ApiOperation("医生提交诊断意见zhl")
    @PostMapping(value = "/submit_suggestion")
    public Object SubmitSuggestion(@RequestHeader String token,@RequestBody SubmitSuggestionForm submitSuggestionForm){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return diagnoseService.SubmitSuggestion(submitSuggestionForm);
    }

    @ApiOperation("获取诊断数据zhl")
    @GetMapping(value = "/get_diagnose_data")
    public Object GetDiagnoseData(@RequestHeader String token,@RequestBody ProgressCheckForm progressCheckForm){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return diagnoseService.GetDiagnoseData(progressCheckForm);
    }

    /**
     * 获取诊断结果
     * @param token
     * @return
     */
    @ApiOperation("获取诊断结果")
    @GetMapping(value = "/get_diagnosis_result")
    public Object getDiagnosisResult(@RequestHeader String token){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        return JSONObject.toJSON(diagnoseService.getDiagnosisResult(openid));
    }

    /**
     * 上传待诊断文件,可传大文件
     * @param token
     * @param updateFileInfo
     * @param file
     * @return
     */
    @ApiOperation("上传待诊断文件")
    @RequestMapping(method = RequestMethod.POST,value = "/submit_diagnose_file",consumes = "multipart/form-data") //,produces = "application/json;charset=UTF-8"
    @ResponseBody
    public Object SubmitPost(@RequestHeader String token , UpdateFileInfo updateFileInfo, @RequestParam("file") MultipartFile file){
        //校验token代码
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("用户开始上传诊断文件>>>>>>>>>>>>");
        return JSONObject.toJSON(diagnoseService.SubmitDiagnoseByUser(updateFileInfo,file));
    }

    /**
     * 获取某用户诊断历史
     * @param token
     * @return
     */
    @ApiOperation("获取某用户诊断历史")
    @GetMapping(value = "/get_diagnose_history_list")
    public Object getHistoryDiagnoseRecordByOpenId(@RequestHeader String token){
        String openid=authorityService.GetUserIdByToken(token);
        authorityService.CheckOpenId(openid);
        log.info("开始获取某用户的诊断历史>>>>>>>>>>>>");
        return JSONObject.toJSON(diagnoseService.getDiagnoseHistoryListByOpenId(openid));
    }
    /**
     * 获取某用户最新诊断结果
     */


}
