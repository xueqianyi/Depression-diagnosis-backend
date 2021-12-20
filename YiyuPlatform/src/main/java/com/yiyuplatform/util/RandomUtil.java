package com.yiyuplatform.util;

import java.util.Random;

/**
 * @Author: Drew
 * @Date: 16:17
 * @Description: 自定义随机工具类
 */
public class RandomUtil {
    public static Integer RandomInt(int low,int up){
        Random random=new Random();
        return random.nextInt(up)+low;
    }
}
