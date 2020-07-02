package com.example.mall.service.impl;


import com.example.mall.dao.SeckillMapper;
import com.example.mall.domain.User;
import com.example.mall.domain.query.QueryUser;
import com.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    SeckillMapper mapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    @Cacheable(cacheNames = "user",key = "#queryUser.name")
    public List<User> list(QueryUser queryUser) {

        return mapper.selectUser(queryUser);
    }

    @Override
    public List<User> nearList(Integer min) {

        List<User> users = mapper.nearList(min);
        return users;
    }


}
