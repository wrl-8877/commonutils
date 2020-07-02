package com.example.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("seckill_order")
public class SeckillOrder implements Serializable {

    /**  */
    private static final long serialVersionUID = 3767006154991129035L;

    @TableId(value = "ORDERID", type = IdType.AUTO)
    private Integer orderId;
    private String orderNo;
    private String tradeNo;
    private Integer orderStatus;
    private String userId;
    private Date createTime;
}
