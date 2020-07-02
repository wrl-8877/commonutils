package com.example.mall.service;

import com.example.mall.domain.User;
import com.example.mall.domain.query.QueryUser;

import java.util.List;

public interface UserService
{
    List<User> list(QueryUser queryUser);

    List<User> nearList(Integer min);


}
