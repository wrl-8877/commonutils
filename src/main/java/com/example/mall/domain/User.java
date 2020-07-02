package com.example.mall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    /**  */
    private static final long serialVersionUID = 3767006154991129035L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long  id;
    private String name;
    private String password;
}
