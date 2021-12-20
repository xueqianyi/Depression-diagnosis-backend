package com.yiyuplatform.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具
 *
 * @auther Xue Qianyi
 * @createTime 9:20
 * @Version 1.0
 */
@Component
@Data
@Slf4j
public class WxConfig {

    public static String access_token;

    //BouncyCastleProvider 这个类里面的方法， 全是静态的，所以必须写道静态代码块里，只运行一次，不然会内存泄露，添加安全服务提供商（加密算法）
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.grantType}")
    private String grantType;
    @Value("${wx.requestUrl}")
    private String requestUrl;
    @Value("${wxToken.url}")
    private String tokenUrl;
    @Value("${wxUnionId.url}")
    private String unionUrl;
    @Value("${wxSubmitPage.url}")
    private String submitPageUrl;
    @Value("${wxSiteSearch.url}")
    private String siteSearchUrl;

//    /** 废弃
//     *  获取全局唯一accessToken,调用微信后台API时需携带此access_token，服务器运行前调用一次
//     * @Param:
//     * @return: java.util.Map<java.lang.String, java.lang.Object>
//     */
//    @SuppressWarnings("unchecked")
//    public Map<String, Object> getUserAccessToken() {
//        Map<String, Object> json = null;
//        try {
//            String accessTokenUrl=tokenUrl+"?"+"grant_type="+grantType+"&appid="+appId+"&secret="+appSecret;
//            // 发送请求
//            String response = HttpRequest.get(accessTokenUrl).body();
//            System.out.println(response);
//            ObjectMapper mapper = new ObjectMapper();
//            json = mapper.readValue(response, Map.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return json;
//    }

    /**
     *  调用微信后台API获取用户session_key
     * @param code 用户通过wx.login获取的code
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSessionByCode(String code) {
        Map<String, Object> json = null;
        try {
            Map<String, String> data = new HashMap<>();
            data.put("appid", appId);
            data.put("secret", appSecret);
            data.put("js_code", code);
            data.put("grant_type", grantType);
            // 发送请求
            String response = HttpRequest.get(requestUrl).form(data).body();
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            log.info("获取sessionKey异常。。。");
            e.printStackTrace();

        }
        return json;
    }

    /**
     *  解密加密数据，得到用户信息
     * @param encryptedData 加密数据
     * @param sessionKey 对称解密秘钥
     * @param iv 对称解密算法初始向量
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> getUserInfo(String encryptedData, String sessionKey, String iv) {
        Map<String, Object> json;
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        byte[] keyByte = Base64.decodeBase64(sessionKey);
        byte[] ivByte = Base64.decodeBase64(iv);
        // 解密用户敏感数据
        try {
            // 如果密钥不是16位的整数倍，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                // 计算需要补足几个16位
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (resultByte != null && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.readValue(result, Map.class);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  废弃
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getUnionId(String token, String openId) throws Exception {
        Map<String, Object> json = null;
        try {
            Map<String, String> data = new HashMap<String, String>();
            data.put("access_token", token);
            data.put("openid", openId);
            String url = unionUrl + "?access_token=" + token + "&openid=" + openId;
            // 发送请求
            String response = HttpRequest.get(unionUrl).form(data).body();
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public int SubmitPages(String path, String Query){
        Map<String,Object> json=null;
        try {
            String url= submitPageUrl+"?access_token="+access_token;
            Map<String,String> data=new HashMap<>();
            data.put("path",path);
            data.put("query",Query);
            String response=HttpRequest.post(url).form(data).body();
            ObjectMapper mapper=new ObjectMapper();
            json=mapper.readValue(response,Map.class);
            // 判断请求成功
            if(json.get("errcode").equals(0)){
                return 0;
            }else{
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public String SiteSearch(String keyword,String next_page_info){
        Map<String,Object> json=null;
        try {
            String url=siteSearchUrl+"?access_token="+access_token;
            Map<String,String> data=new HashMap<>();
            data.put("keyword",keyword);
            data.put("next_page_info",next_page_info);
            String response=HttpRequest.post(url).form(data).body();
            ObjectMapper mapper=new ObjectMapper();
            json=mapper.readValue(response,Map.class);
            // 判断请求成功
            return JSON.toJSONString(json);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}