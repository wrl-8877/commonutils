package com.example.mall.service;



import com.example.mall.domain.SeckillActivity;

import java.util.List;

public interface SeckillActivityService {

    List<SeckillActivity> findUnstartSeckill();

    String seckill(int id, String userId);
}
