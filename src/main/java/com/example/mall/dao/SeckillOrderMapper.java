package com.example.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.domain.SeckillOrder;

public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {

   int insertOrder(SeckillOrder order);

   SeckillOrder findbyOrderNo(String orderNo);
}
