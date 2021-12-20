package com.yiyuplatform.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @description: 知识类别数据表
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:03
 */
@Data
@Entity
@Table(name = "kno_category")
public class KnoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名称 如：生活、睡眠、男性、女性等
     */
    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    /**
     * 类目编号
     */
    @Column(name = "CATEGORY_TYPE")
    private Integer categoryType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "UPDATE_TIME")
    private  Date updateTime;
}
