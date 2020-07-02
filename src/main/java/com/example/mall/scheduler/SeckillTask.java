package com.example.mall.scheduler;

import com.example.mall.domain.User;
import com.example.mall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeckillTask {

    @Autowired
    UserService userService;

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void doTask(){
        List<User> users = userService.nearList(5);
        System.out.println(users);
    }
}
