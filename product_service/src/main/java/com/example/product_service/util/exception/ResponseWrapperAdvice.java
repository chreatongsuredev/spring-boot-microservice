package com.example.product_service.util.exception;

import com.example.product_service.util.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // apply to all responses
    }
   //Class<? extends HttpMessageConverter<?>> selectedConverterTyp
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse  response) {

        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        int status = serverHttpResponse.getServletResponse().getStatus();

        if(status >= 400){
            return body;
        }
        if(body instanceof ApiResponse){
            return body;
        }
        return new ApiResponse<>(true ,"success",body);
    }
}
