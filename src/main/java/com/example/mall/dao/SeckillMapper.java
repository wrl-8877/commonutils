package com.example.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.domain.User;
import com.example.mall.domain.query.QueryUser;
import com.example.sms.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SeckillMapper extends BaseMapper<User> {

    List<User> selectUser(QueryUser queryUser);

    List<User> nearList(Integer min);


}
