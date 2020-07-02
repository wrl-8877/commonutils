package com.example.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("seckill_activity")
public class SeckillActivity implements Serializable {

    private static final long serialVersionUID = 3767006154991129035L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private String goodsId;
    private Long secCount;
    private Date startTime;
    private Date endTime;
    private int status;
    private BigDecimal price;
}
