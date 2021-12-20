package com.yiyuplatform.util;

import java.util.Random;

/**
 * @description:
 * @author: Xue Qianyi
 * @date: 2021-12-02 15:42
 */
public class KeyUtil {
    public static synchronized String createUniqueKey(){
        Random random = new Random();
        Integer key = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(key);
    }
}
