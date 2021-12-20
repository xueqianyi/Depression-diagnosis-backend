package com.yiyuplatform.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/13
 * \* Time: 15:02
 * \* Description:解密算法
 * \
 */
public class DecodeUtil {
    /**
     *  Sha1解码
     * @Param: str 待解码字符串
     * @return: String 解码后的字符串
     */
    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @param text 明文
     * @param key 密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text, String key) {
        // 加密后的字符串

        byte[] md5str = DigestUtils.md5Digest((text+key).getBytes());
        return new String(md5str);
    }

    /**
     * 不带秘钥加密
     * @param text
     * @return java.lang.String
     */
    public static String md5noKey(String text){
        // 加密后的字符串
        byte[] md5str = DigestUtils.md5Digest(text.getBytes());
        return new String(md5str);
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     */
    public static boolean verify(String text, String key, String md5) {
        String md5str = md5(text,key);
        if (md5str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
