package com.yiyuplatform.util;


import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Drew
 * @Date: 9:36
 * @Description: 自定义JSON工具类
 */
public class JsonUtil {
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map<String,Object> Obj2Map(Object obj) throws Exception{
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
