package com.yiyuplatform.repository;

import com.yiyuplatform.entity.DiagnoseTRecords;
import com.yiyuplatform.vo.diagnose.DiagnoseInfoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/10/28
 * \* Time: 8:12
 * \* Description: DiagnoseTRecords接口类
 * \
 */

public interface DiagnoseTRecordsRepository extends JpaRepository<DiagnoseTRecords, Integer> {

    /**
     * 根据病人Id查找
     * @param openid
     * @return java.util.List<com.yiyuplatform.entity.DiagnoseTRecords>
     */
    @Query("select di from DiagnoseTRecords di where di.diPatientId=?1")
    List<DiagnoseTRecords> findByDiPatientId(String openid);
   

    /**
     * 根据诊断单号返回数据
     * @param diId
     * @return com.yiyuplatform.entity.DiagnoseTRecords
     */
    @Query("select di from DiagnoseTRecords di where di.diId=?1")
    DiagnoseTRecords findByDiId(String diId);

    /**
     * 根据诊断单号返回诊断流程表单的相关信息（联合用户表）
     * @param diId
     * @return com.yiyuplatform.vo.diagnose.DiagnoseVO.ProgressCheck
     */
    @Query("select new com.yiyuplatform.vo.diagnose.DiagnoseInfoVO(d.diStatus,d.diPatientNkname,d.diType,u.ciNkname,d.diDoctorSug,d.diSubmittime,d.diAnltime,d.diDitime,d.diId)"+
    "from DiagnoseTRecords d LEFT join TUserinfo u on d.diDoctorid=u.ciId WHERE d.diId=?1 group by d.diDoctorid")
    DiagnoseInfoVO getDiagnoseVO(String diId);

    @Query("update DiagnoseTRecords di set di.diDoctorSug=?2 where di.diDoctorid=?1")
    @Modifying
    void UpdateSuggestion(String diId,String suggestion);

}
