package com.yiyuplatform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@SpringBootApplication
@EnableScheduling
public class YiyuPlatformApplication{

    public static void main(String[] args) {
        SpringApplication.run(YiyuPlatformApplication.class, args);
    }


}
