package com.yiyuplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: Xue Qianyi
 * \* Date: 2021/11/6
 * \* Time: 19:38
 * \* Description: 配置API管理平台 /doc.html
 * \
 */
public class SwaggerConfiguration implements WebMvcConfigurer {
    @Bean(value = "userApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yiyuplatform.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("接口测试")
                .description("websocket")
                .termsOfServiceUrl("http://www.coding-space.cn/")
                .contact(new Contact("测试", "www.baidu.com", "569775346@qq.com"))
                .version("1.0")
                .build();
    }

}
