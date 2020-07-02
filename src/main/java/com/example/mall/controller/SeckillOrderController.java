package com.example.mall.controller;


import com.example.mall.domain.CommonResult;
import com.example.mall.domain.SeckillOrder;
import com.example.mall.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class SeckillOrderController {

    @Autowired
    SeckillOrderService orderService;

    @GetMapping("/order/{orderNo}")
    @ResponseBody
    public CommonResult findOrder(@PathVariable String orderNo){
        SeckillOrder order = orderService.findOrder(orderNo);
        if(order!=null){
            return CommonResult.ok("及时付款");
        }else{
            return CommonResult.error("继续等待");
        }
    }

    @GetMapping("/pay")
    public ModelAndView pay(){
        ModelAndView view = new ModelAndView("/pay");
        return view;
    }
}
