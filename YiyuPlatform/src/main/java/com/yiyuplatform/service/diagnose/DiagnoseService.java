package com.yiyuplatform.service.diagnose;

import com.yiyuplatform.entity.DiagnosisTRes;
import com.yiyuplatform.form.diagnose.UpdateFileInfo;
import com.yiyuplatform.form.diagnose.ProgressCheckForm;
import com.yiyuplatform.form.diagnose.SubmitDiagnoseForm;
import com.yiyuplatform.form.diagnose.SubmitSuggestionForm;
import com.yiyuplatform.vo.diagnose.DiagnoseHistoryVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙 薛芊祎
 * \* Date: 2021/11/8
 * \* Time: 14:13
 * \* Description:
 * \
 */
public interface DiagnoseService {
    Object SubmitDiagnose(SubmitDiagnoseForm submitDiagnoseForm) throws Exception;
    Object ProgressCheck(String userid,String formid);
    Object SubmitSuggestion(SubmitSuggestionForm submitSuggestionForm);
    Object GetDiagnoseData(ProgressCheckForm progressCheckForm);
    List<DiagnosisTRes> getDiagnosisResult(String openId);

    //用户提交待诊断文件
    String SubmitDiagnoseByUser(UpdateFileInfo updateFileInfo, MultipartFile file);

    //返回某用户的诊断历史列表
    List<DiagnoseHistoryVO> getDiagnoseHistoryListByOpenId(String openId);


}
