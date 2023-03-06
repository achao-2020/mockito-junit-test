package com.example.mokitotestdemo.global;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.List;

/**
 * 统一controller返回对象<br/>
 * 默认前端过来的请求统一返回Result或者PageResult<br/>
 * 服务之间调用不用统一返回Result对象
 *
 */
@Slf4j
@ControllerAdvice
public class ResponseWrapperBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (returnType.getMethod() == null) {
            return true;
        }
        return !"error".equals(returnType.getMethod().getName());
    }

    @Override
    public Result beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return (Result) body;
        }
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setData(body);
        return result;
    }
}
