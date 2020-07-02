package com.example.mall.service;


import com.example.mall.domain.SeckillOrder;

public interface SeckillOrderService {

    String sendOrdertoQueue(String userId);

    SeckillOrder findOrder(String orderNo);
}
