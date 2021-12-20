package com.yiyuplatform.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description: 上传图片用的工具类
 * @author: Xue Qianyi
 * @date: 2021-12-08 12:18
 */
@Slf4j
public class UploadUtils {
    //定义一个目标路径,就是我们要把图片上传到的位置
    public static final String BASE_PATH = "E:\\YYPlatfrom\\backend\\YiyuPlatform\\src\\main\\resources\\static\\";

    //定义访问图片的路径
    public static final String SERVER_PATH = "http://localhost:8080/";


    //上传方法
    public static String upload(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();

        //为了保证图片在服务器中名字的唯一性，这个要用UUID来对filename进行改写。
        String uuid = UUID.randomUUID().toString().replace("-","");

        //将生成的UUID和filenae拼接。
        String newFileName = uuid + "-" + filename;

        log.info("用户上传的图片名称"+newFileName);

        //创建一个文件实例对象
        File image = new File(BASE_PATH,newFileName);

        //对这个图片进行上传操作
        try{
            file.transferTo(image);
        }catch (IOException e){
            e.printStackTrace();
        }

        return newFileName;

    }

}
