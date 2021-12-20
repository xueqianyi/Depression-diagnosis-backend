package com.yiyuplatform.repository;

import com.yiyuplatform.entity.TDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author XueQianyi
 * @Date 2021/11/22 21:23
 * @Description  OnlineDoctor接口类
 */
public interface TDoctorRepository extends JpaRepository<TDoctor,Integer> {
}
