package com.yiyuplatform.repository;

import com.yiyuplatform.entity.DiagnosisTRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 诊断结果
 * @author: Xue Qianyi
 * @date: 2021-12-08 11:20
 */
public interface DiagnosisResultRepository extends JpaRepository<DiagnosisTRes,String> {
    /**
     * 返回诊断列表
     * @return
     */
    public List<DiagnosisTRes> findByDiPatientId(String diPatientId);
}
