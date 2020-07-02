package com.example.mall.exception;

import lombok.Data;

@Data
public class SeckillException  extends Exception{

    private String msg;

    public SeckillException(String message) {
        super(message);
    }
}
