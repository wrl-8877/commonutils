package com.example.mall.service.impl;


import com.example.mall.dao.SeckillOrderMapper;
import com.example.mall.domain.SeckillOrder;
import com.example.mall.service.SeckillOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SeckillOrdererviceImpl implements SeckillOrderService {

    @Resource
    SeckillOrderMapper orderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String sendOrdertoQueue(String userId){
        String orderNo = UUID.randomUUID().toString().replace("-","");
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("orderNo",orderNo);
        log.info("{} 创建了订单 {}",userId,orderNo);
        rabbitTemplate.convertAndSend("pay.exchage","pay.binding",map);

        log.error("{} 创建了订单 {}",userId,orderNo);
        return orderNo;
    }

    @RabbitListener(queues = "pay.queue")
    @RabbitHandler
    public void getMsg(Message message, Channel channel, @Payload Map<String,String> map){
        //{"orderNo":"f0d2a256e9cd42278ba13ada597a87a7","userId":"user-3"}'
        log.error("Message为 {}",message);
        log.error("获取到订单 {}",map);

        //TO DO LIST
        //对接支付宝，物流，日志登记
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String,String> result = map;
        String orderNo = result.get("orderNo");
        String userId = result.get("userId");

        SeckillOrder order = new SeckillOrder();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setOrderStatus(0);
        order.setTradeNo(UUID.randomUUID().toString().replace("-",""));

        int flag = orderMapper.insertOrder(order);

        try {
            if(flag>0) {
                //订单创建成功，确认消息，false表示每次仅接受一个请求
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }else{
                //订单创建失败，拒绝消息，回滚给其他消费者
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(map);
    }

    @Override
    public SeckillOrder findOrder(String orderNo) {
        return orderMapper.findbyOrderNo(orderNo);
    }
}
