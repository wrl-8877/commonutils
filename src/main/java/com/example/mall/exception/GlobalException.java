package com.example.mall.exception;

import com.example.mall.domain.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public CommonResult getException(Exception e){
        return CommonResult.error(e.getMessage());
    }
}
