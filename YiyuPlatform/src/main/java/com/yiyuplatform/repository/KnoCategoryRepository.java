package com.yiyuplatform.repository;

import com.yiyuplatform.entity.KnoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 知识类别
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:07
 */
@Repository
public interface KnoCategoryRepository extends JpaRepository<KnoCategory,Integer> {
    //按照分类类别查找
    public KnoCategory findByCategoryType(Integer categoryType);
    //按照类别名称查找
    public KnoCategory findByCategoryName(String categoryName);

}
