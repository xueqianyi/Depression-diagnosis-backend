package com.yiyuplatform.util;

import lombok.Data;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-06 20:47
 */
@Data
public class SearchResultVO<T> {
    private Boolean hasMore;
    private T data; //泛型，有很多种类型数据
}
