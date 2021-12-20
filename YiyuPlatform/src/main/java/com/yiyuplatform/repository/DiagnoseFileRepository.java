package com.yiyuplatform.repository;

import com.yiyuplatform.entity.DiagnoseFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 上传诊断文件
 * @author: Xue Qianyi
 * @date: 2021-12-08 22:27
 */
public interface DiagnoseFileRepository extends JpaRepository<DiagnoseFile,String> {

}
