package com.yiyuplatform.service.Doctor;

import com.yiyuplatform.entity.TDoctor;
import com.yiyuplatform.vo.Doctor.DoctorDetailVO;
import com.yiyuplatform.vo.Doctor.DoctorListVO;


import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-03 0:54
 */
public interface DoctorFirstPageService {

    //获取医生的详情信息
    public DoctorDetailVO findDetailByDocId(String docId);
    //获取首页知识列表
    public List<DoctorListVO> findDoctorList();

}
