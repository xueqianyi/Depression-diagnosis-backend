package com.yiyuplatform.service.search.factSearch;

import com.yiyuplatform.util.SearchResultVO;

/**
 * @description: 帖子搜索接口
 * @author: Xue Qianyi
 * @date: 2021-12-07 2:38
 */
public interface PostSearchService {
    public SearchResultVO searchForPost(String keyWords, String content, int page, int size);
}
