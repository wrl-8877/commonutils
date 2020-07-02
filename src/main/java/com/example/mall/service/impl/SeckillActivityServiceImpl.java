package com.example.mall.service.impl;


import com.example.mall.dao.SeckillActivityMapper;
import com.example.mall.domain.SeckillActivity;
import com.example.mall.service.SeckillActivityService;
import com.example.mall.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SeckillActivityServiceImpl implements SeckillActivityService {
    @Resource
    SeckillActivityMapper mapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    private  String redisKey = "seckill::count::";

    private String userRedisKey = "seckill::users::";

    @Autowired
    SeckillOrderService orderService;

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public List<SeckillActivity> findUnstartSeckill() {
        List<SeckillActivity> activities= mapper.findUnstartSeckill();
        for (SeckillActivity activity:activities){
            System.out.println(activity.getId() + " 号商品秒杀活动已启动");
            //删除预存
            redisTemplate.delete(redisKey+activity.getId());
            //库存放入redis中
            Long secCount = activity.getSecCount();
            String obj = null;
            for (int i = 0; i < secCount; i++) {
                redisTemplate.opsForList().rightPush(redisKey+activity.getId(),activity.getGoodsId());
            }
            //更新活动状态
            activity.setStatus(1);

            mapper.update(activity);
        }
        return activities;
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void deleteEndSeckill(){
        List<SeckillActivity> activities = mapper.selectEndSeckill();
        for(SeckillActivity activity:activities){
            System.out.println(activity.getId() +" 秒杀活动已结束");
            //删除id
            redisTemplate.delete(redisKey+activity.getId());
            //更新活动状态
            activity.setStatus(2);
            mapper.update(activity);
        }
    }

    @Override
    public String seckill(int id,String userId) {
        SeckillActivity activity = mapper.findById(id);
        if (activity==null){
            throw new SecurityException("活动不存在");
        }
        else if (activity.getStatus()==0){
            throw new SecurityException("活动未开始");
        }
        else if (activity.getStatus()==2){
            throw new SecurityException("活动已结束");
        }
        Boolean member = redisTemplate.opsForSet().isMember(userRedisKey + activity.getId(), userId);
        if(member){
            throw new SecurityException("每位用户限购一个");
        }
        //从库存中减一个
        String s = redisTemplate.opsForList().leftPop(redisKey + activity.getId());

        if(s!=null) {
            //放入set中 seckill::users::id - userId
            redisTemplate.opsForSet().add(userRedisKey + activity.getId(), userId);
        }else{
            throw new SecurityException("抱歉，该商品已被抢光");
        }

        String orderNo = orderService.sendOrdertoQueue(userId);
        return orderNo;
    }


}
