package com.yiyuplatform.repository;

import com.yiyuplatform.entity.KnoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @description: 知识详情页
 * @author: Xue Qianyi
 * @date: 2021-11-24 18:53
 */
public interface KnoInfoRepository extends JpaRepository<KnoInfo,Integer> {
    //按照是否为热门知识查找
    public List<KnoInfo> findByKnoIshot(Integer knoIshot);
    //按照知识类别查找
    public List<KnoInfo> findByCategoryType(Integer categoryType);
    //按照ID查找
    public KnoInfo findByKnoCardId(Integer knoCardId);

    //更新阅读量
    @Modifying
    @Query("update KnoInfo kno set kno.knoCardRead = kno.knoCardRead + ?2 where kno.knoCardId = ?1")
    @Transactional
    void UpdateReadNumKnowledge(Integer knoCardId,Integer addNum);

}
