package com.yiyuplatform.service.Doctor.impl;

import com.yiyuplatform.entity.TDoctor;
import com.yiyuplatform.entity.TUserinfo;
import com.yiyuplatform.repository.FirstPageTDoctorRepository;
import com.yiyuplatform.repository.TUserInfoRepository;
import com.yiyuplatform.service.Doctor.DoctorFirstPageService;
import com.yiyuplatform.vo.Doctor.DoctorDetailVO;
import com.yiyuplatform.vo.Doctor.DoctorListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-03 0:55
 */
@Slf4j
@Service
@Transactional
public class DoctorFirstPageServiceImpl implements DoctorFirstPageService {

    @Autowired
    FirstPageTDoctorRepository firstPageTDoctorRepository;
    @Autowired
    TUserInfoRepository tUserInfoRepository;



    @Override
    public DoctorDetailVO findDetailByDocId(String docId) {
        //查找到具体信息
        TUserinfo userinfo = tUserInfoRepository.findByCiId(docId);
        TDoctor tDoctor = firstPageTDoctorRepository.findByDocId(docId);

        DoctorDetailVO doctorDetailVO2 = new DoctorDetailVO();
        doctorDetailVO2.setDocId(docId);
        doctorDetailVO2.setCiRealname(userinfo.getCiRealname());
        doctorDetailVO2.getCiPhonenumber(userinfo.getCiPhonenumber());
        doctorDetailVO2.setCiSex(userinfo.getCiSex());
        doctorDetailVO2.setDocCertification(tDoctor.getDocCertification());
        doctorDetailVO2.setDocAbstract(tDoctor.getDocAbstract());
        doctorDetailVO2.setDocPhoto(tDoctor.getDocPhoto());
        doctorDetailVO2.setDocDepartment(tDoctor.getDocDepartment());
        doctorDetailVO2.setDocPost(tDoctor.getDocPost());
        doctorDetailVO2.setDocHospital(tDoctor.getDocHospital());
        return doctorDetailVO2;
    }

    @Override
    public List<DoctorListVO> findDoctorList() {
        List<DoctorListVO> doctorListVOList = new ArrayList<> ();
        List<TDoctor> tDoctorList = firstPageTDoctorRepository.findAll();
        for (TDoctor tDoctor : tDoctorList) {
            //openId
            String ciId = tDoctor.getDocId();
            //查找名字等信息
            TUserinfo userinfo = tUserInfoRepository.findByCiId(ciId);

            //装载数据
            DoctorListVO doctorListVO = new DoctorListVO();


            doctorListVO.setDocId(ciId);
            doctorListVO.setCiRealname(userinfo.getCiRealname());

            doctorListVO.setCiSex(userinfo.getCiSex());

            doctorListVO.setDocPhoto(tDoctor.getDocPhoto());
            doctorListVO.setDocDepartment(tDoctor.getDocDepartment());
            doctorListVO.setDocPost(tDoctor.getDocPost());
            doctorListVO.setDocHospital(tDoctor.getDocHospital());

            doctorListVOList.add(doctorListVO);
        }
        return doctorListVOList;
    }
}
