package com.example.product_service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse(boolean success,String message,T data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String message , T data){
        return new ApiResponse<>(true,message,data);
    }

    public static <T> ApiResponse<T> error(String message){
        return new ApiResponse<>(false,message,null);
    }

}
