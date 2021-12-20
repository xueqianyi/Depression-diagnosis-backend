package com.yiyuplatform.service.diagnose.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiyuplatform.config.RedisConfig;
import com.yiyuplatform.config.Status;
import com.yiyuplatform.entity.*;
import com.yiyuplatform.exception.ExceptionManager;
import com.yiyuplatform.form.diagnose.UpdateFileInfo;
import com.yiyuplatform.form.diagnose.ProgressCheckForm;
import com.yiyuplatform.form.diagnose.SubmitDiagnoseForm;
import com.yiyuplatform.form.diagnose.SubmitSuggestionForm;
import com.yiyuplatform.repository.*;
import com.yiyuplatform.service.diagnose.DiagnoseService;
import com.yiyuplatform.service.websocket.WebSocketService;
import com.yiyuplatform.util.JsonUtil;
import com.yiyuplatform.util.ListSortUtil;
import com.yiyuplatform.util.RandomUtil;
import com.yiyuplatform.util.UploadUtils;
import com.yiyuplatform.vo.diagnose.DiagnoseDataVO;
import com.yiyuplatform.vo.diagnose.DiagnoseHistoryVO;
import com.yiyuplatform.vo.diagnose.DiagnoseInfoVO;
import com.yiyuplatform.vo.socketobj.PushMsgVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Date;
import javax.transaction.Transactional;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Drew,Xueqianyi
 * @Date: 9:17
 *  DiagnoseFormImpl 负责提交诊断表单 进度控制 进度查询业务
 */
@Slf4j
@Service
public class DiagnoseServiceImpl implements DiagnoseService {
    //诊断结果
    @Autowired
    DiagnosisResultRepository diagnosisResultRepository;

    @Autowired
    TUserInfoRepository tUserInfoRepository;

    @Autowired
    DiagnoseTRecordsRepository diagnoseRepo;
    @Autowired
    TDoctorRepository doctorRepository;
    @Autowired
    WebSocketService webSocketService;
    //诊断文件
    @Autowired
    DiagnoseFileRepository diagnoseFileRepository;

    @Override
    @Transactional
    /**
     * 提交诊断
     * @param submitDiagnoseForm
     * @return java.lang.Object
     */
    public Object SubmitDiagnose(SubmitDiagnoseForm submitDiagnoseForm) throws Exception{
        try {
            String patientId=submitDiagnoseForm.getOpenId();
            Integer dType;
            do{
                if(!StringUtils.hasText(submitDiagnoseForm.getActivityUrl())&&
                        !StringUtils.hasText(submitDiagnoseForm.getAudioUrl())){
                    throw ExceptionManager.create(Status.CustomErrors.ParamError.getErrorCode(),
                            Status.CustomErrors.ParamError.getErrorMsg());
                }
                if(!StringUtils.hasText(submitDiagnoseForm.getAudioUrl())){
                    dType=0;
                    break;
                }
                if(!StringUtils.hasText(submitDiagnoseForm.getActivityUrl())){
                    dType=1;
                    break;
                }
                dType=2;
            }while(false);
            // 如果没有上传数据 则不受理此次诊断

            TUserinfo patient=tUserInfoRepository.findByCiId(patientId);
            // 用户不存在
            if(patient==null){
                throw ExceptionManager.create(Status.CustomErrors.UnknownUser.getErrorCode(), Status.CustomErrors.UnknownUser.getErrorMsg());
            }
            // 用户未认证
            if(patient.getCiStatus()!=2){
                throw ExceptionManager.create(Status.CustomErrors.PermissionDenied.getErrorCode(), Status.CustomErrors.PermissionDenied.getErrorMsg());
            }
            DiagnoseTRecords newDiagnose=new DiagnoseTRecords();

            // 医生分配，并推送给医生
            List<TDoctor> doctorList=doctorRepository.findAll();
            Integer indexedDoctor= RandomUtil.RandomInt(0,doctorList.size());
            String docId=doctorList.get(indexedDoctor).getDocId();



            // 定义其他需要插入到表中的数据
            Integer dStatus=0;
            Timestamp submitTime=new Timestamp(System.currentTimeMillis());
            Integer userAck=0;

            newDiagnose.setDiId(UUID.randomUUID().toString());
            newDiagnose.setDiPatientId(patient.getCiId());
            newDiagnose.setDiPatientNkname(patient.getCiNkname());
            newDiagnose.setDiPatientComment(submitDiagnoseForm.getPatientComment());
            newDiagnose.setDiType(dType);
            newDiagnose.setDiStatus(dStatus);
            newDiagnose.setDiDoctorid(docId);
            newDiagnose.setDiSubmittime(submitTime);
            newDiagnose.setDiAck(userAck);
            newDiagnose.setDiDataAct(submitDiagnoseForm.getActivityUrl());
            newDiagnose.setDiDataAud(submitDiagnoseForm.getAudioUrl());
//            数据入库
            diagnoseRepo.save(newDiagnose);

            /**
             * 提交数据给python模型
             * code here
             * */

            //推送消息给被分配的医生
            PushMsgVO doctorMsg=new PushMsgVO();
            doctorMsg.setContent("您有一个新的病人需要诊断");
            doctorMsg.setType(Status.DIAGNOSE_MSG);
            // 推送新消息给医生
            Map<String,Object> doctorMsgJson=JSONObject.parseObject(JSON.toJSONString(doctorMsg));
            webSocketService.sendMsg(doctorMsgJson,docId,RedisConfig.notificationEvent);


            // 推送消息给病人
            PushMsgVO patientMsg=new PushMsgVO();
            patientMsg.setContent("您的诊断提交成功");
            patientMsg.setType(Status.DIAGNOSE_MSG);
            Map<String,Object> patientMsgJson=JSONObject.parseObject(JSON.toJSONString(patientMsg));
            webSocketService.sendMsg(patientMsgJson,patientId,RedisConfig.notificationEvent);

            String msg="成功";
            return JSONObject.toJSON(msg);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    /**
     * 进度查看
     * @param userid
     * @param formid
     * @return java.lang.Object
     */
    public Object ProgressCheck(String userid, String formid) throws RuntimeException{
        try{
            DiagnoseTRecords formInfo=diagnoseRepo.findByDiId(formid);
            // 如果表单病人号和请求Id不同
            if(!formInfo.getDiPatientId().equals(userid)){
                throw ExceptionManager.create(Status.CustomErrors.PermissionDenied.getErrorCode(), Status.CustomErrors.PermissionDenied.getErrorMsg());
            }
            DiagnoseInfoVO progressInfo = diagnoseRepo.getDiagnoseVO(formid);
            return JSONObject.toJSON(progressInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    /**
     * 诊断意见提交
     * @param submitSuggestionForm
     * @return java.lang.Object
     */
    public Object SubmitSuggestion(SubmitSuggestionForm submitSuggestionForm) throws RuntimeException {
        try{
            DiagnoseTRecords tRecords=diagnoseRepo.findByDiId(submitSuggestionForm.getDiagnoseId());
            if(tRecords==null){
                throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataBaseError.getErrorMsg());
            }
            if (submitSuggestionForm.getDoctorId().equals(tRecords.getDiDoctorid())) {
                diagnoseRepo.UpdateSuggestion(submitSuggestionForm.getDiagnoseId(),submitSuggestionForm.getSuggestion());
            }
            return JSONObject.toJSON(submitSuggestionForm.getSuggestion());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    /**
     * 获取诊断数据
     * @param progressCheckForm
     * @return java.lang.Object
     */
    public Object GetDiagnoseData(ProgressCheckForm progressCheckForm) throws RuntimeException{
        try {
            DiagnoseTRecords tRecords=diagnoseRepo.findByDiId(progressCheckForm.getFormId());
            DiagnoseDataVO data=new DiagnoseDataVO();
            File dataFile=new File(tRecords.getDiAnlResult());
            do{
                // 不为空
                if(tRecords==null){
                    throw ExceptionManager.create(Status.CustomErrors.ParamError.getErrorCode(), Status.CustomErrors.ParamError.getErrorMsg());
                }
                // 判断是该条诊断记录查询人是对应医生或者病人
                if(!(tRecords.getDiPatientId().equals(progressCheckForm.getUserId())||tRecords.getDiDoctorid().equals(progressCheckForm.getUserId()))) {
                    throw ExceptionManager.create(Status.CustomErrors.PermissionDenied.getErrorCode(), Status.CustomErrors.PermissionDenied.getErrorMsg());
                }
                // 判断分析结果不为空
                if(!StringUtils.hasText(tRecords.getDiAnlResult())){
                    throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataNotExist.getErrorMsg());
                }
                // 判断分析结果文件是否存在
                if(!dataFile.exists()){
                    throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataNotExist.getErrorMsg());
                }
                // 判断是否是文件
                if(!dataFile.isFile()){
                    throw ExceptionManager.create(Status.CustomErrors.DataNotExist.getErrorCode(), Status.CustomErrors.DataNotExist.getErrorMsg());
                }
                String jsonString = JsonUtil.readJsonFile(JsonUtil.readJsonFile(tRecords.getDiAnlResult()));
                return JSONObject.parseObject(jsonString);
            }while(false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 得到某用户的全部诊断结果
     * @param openId
     * @return
     */
    @Override
    public List<DiagnosisTRes> getDiagnosisResult(String openId) {

        List<DiagnosisTRes> diagnosisResultList = diagnosisResultRepository.findByDiPatientId(openId);
        return diagnosisResultList;
    }

    /**
     * 用户上传待诊断文件（XQY）
     * @param updateFileInfo
     * @param file
     * @return
     */
    @Override
    public String SubmitDiagnoseByUser(UpdateFileInfo updateFileInfo, MultipartFile file) {
        try{
            //上传诊断文件
            String diagnosePath = UploadUtils.upload(file);
            DiagnoseFile diagnoseFile = new DiagnoseFile();
            //生成ID
            String id = UUID.randomUUID().toString();
            diagnoseFile.setDfId(id);

            //文件路径
            diagnoseFile.setDfFilePath(diagnosePath);
            //提交记录的用户的openId
            diagnoseFile.setDfPatinetId(updateFileInfo.getUserId());
            //前端获取时间
            diagnoseFile.setDfSubmitTime(updateFileInfo.getSubmitDate());
            //保存
            diagnoseFileRepository.save(diagnoseFile);
            return "上传文件成功！";
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 返回某用户的诊断历史列表
     * @param openId
     * @return
     */
    @Override
    public List<DiagnoseHistoryVO> getDiagnoseHistoryListByOpenId(String openId) {
        //用户信息
        TUserinfo tUserinfo = tUserInfoRepository.findByCiId(openId);

        if(diagnosisResultRepository.findByDiPatientId(openId).size() == 0){
            //用户还未诊断过
            return null;
        }else{
            //需要按照诊断时间排序
            //先获取对应用户的数据
            List<DiagnosisTRes> diagnosisTResList = diagnosisResultRepository.findByDiPatientId(openId);
            //按照时间降序排序
            ListSortUtil<DiagnosisTRes> sortList = new ListSortUtil<DiagnosisTRes>();
            sortList.sort(diagnosisTResList, "updateTime", "desc");
            List<DiagnoseHistoryVO> diagnoseHistoryVOList = new ArrayList<>();
            for (DiagnosisTRes diagnosisTRes : diagnosisTResList) {
                DiagnoseHistoryVO diagnoseHistoryVO = new DiagnoseHistoryVO();
                diagnoseHistoryVO.setRealName(tUserinfo.getCiRealname());
                diagnoseHistoryVO.setSex(tUserinfo.getCiSex());
                diagnoseHistoryVO.setDiagnoseResult(diagnosisTRes.getDiResult());
                diagnoseHistoryVO.setUserAvatar(tUserinfo.getCiAvatarturl());
                Date date = diagnosisTRes.getUpdateTime();
                //时间转化
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String sdf1 = f.format(date);
                diagnoseHistoryVO.setUpdateTime(sdf1);
                diagnoseHistoryVO.setId(diagnosisTRes.getDiId());
                diagnoseHistoryVOList.add(diagnoseHistoryVO);
            }
            return diagnoseHistoryVOList;
        }
    }


}
