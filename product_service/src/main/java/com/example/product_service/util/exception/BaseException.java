package com.example.product_service.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{
    private  HttpStatus status;
    private  String errorCode;
    private  String information;

    public BaseException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public BaseException(String information,String message, HttpStatus status, String errorCode) {
        super(message);
        this.information = information;
        this.status = status;
        this.errorCode = errorCode;
    }

    public BaseException(String information,String message, Throwable cause, HttpStatus status, String errorCode) {
        super(message, cause);
        this.information = information;
        this.status = status;
        this.errorCode = errorCode;
    }

}
