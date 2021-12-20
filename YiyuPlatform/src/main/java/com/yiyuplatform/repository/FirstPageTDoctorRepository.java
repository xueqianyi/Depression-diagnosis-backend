package com.yiyuplatform.repository;

import com.yiyuplatform.entity.TDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 首页的医生信息列表以及详情页
 * @author: Xue Qianyi
 * @date: 2021-12-03 0:51
 */
public interface FirstPageTDoctorRepository extends JpaRepository<TDoctor,String> {
    //利用OpenId查找医生信息
    public TDoctor findByDocId(String docId);
}
