package com.yiyuplatform.service.search.factSearch;

import com.yiyuplatform.util.SearchResultVO;

/**
 * @description: 知识搜索接口
 * @author: Xue Qianyi
 * @date: 2021-12-06 20:08
 */
public interface KnowledgeSearchService {
    public SearchResultVO search(String keyWords, String content, int page, int size);
}
