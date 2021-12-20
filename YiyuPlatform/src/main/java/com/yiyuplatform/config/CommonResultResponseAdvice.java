package com.yiyuplatform.config;

/**
 * \* Date: 2021/11/23
 * \* Time: 22:55
 * \* Description: 此类拦截Controller方法默认返回参数，统一处理返回值/响应体
 * \
 */
import com.yiyuplatform.vo.CommonResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableWebMvc
@Configuration
@RestControllerAdvice(basePackages = "com.yiyuplatform.controller")
public class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果发生异常，则返回的body直接就是CommonResult了，这时候直接返回body即可
        if (body instanceof CommonResult) {
            return body;
        }
        return new CommonResult<>(body);
    }

}
