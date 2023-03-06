package com.example.mokitotestdemo.exception;

/**
 * @author licc3
 * @date 2023-2-16 14:37
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}