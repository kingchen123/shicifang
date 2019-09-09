package com.pingan.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 公共异常处理类,controller层发生错误时,都会跳转到这个类来
 */
@RestControllerAdvice//拦截异常并统一处理
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
       e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage() );
    }

}
