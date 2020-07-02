package com.example.mall.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommonResult {

    private Integer code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();

    public static CommonResult ok(String msg) {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.SUCCESS);
        result.setMsg(msg);

        return result;
    }

    public static CommonResult error(String msg) {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.ERROR);
        result.setMsg(msg);
        return result;
    }


    public CommonResult data(String msg, Object obj){
        this.data.put(msg,obj);
        return this;
    }
}
