package com.example.mall.controller;


import com.example.mall.domain.User;
import com.example.mall.domain.query.QueryUser;
import com.example.mall.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class SeckillController {

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    Configuration freemarkerConfig;

    @GetMapping("/goods")
    public ModelAndView seckill(Long userId){
        ModelAndView view = new ModelAndView("/goods");
        view.addObject("msg",userId);
        System.out.println(userId);
        return  view;
    }

    @GetMapping("/cache/{name}/{password}")
    @ResponseBody
    public List<User> cache(@PathVariable String name, @PathVariable String password){
        redisTemplate.expire("user::"+name,2, TimeUnit.MINUTES);
        QueryUser queryUser = new QueryUser();
        queryUser.setName(name);
        queryUser.setPassword(password);
        List<User> list = userService.list(queryUser);
        return list;
    }


    @GetMapping("/static")
    @ResponseBody
    public String doStatic(Long userId) throws IOException, TemplateException {

       Template template = freemarkerConfig.getTemplate("goods.ftlh");
       Map<String,Object> map = new HashMap<>();
       map.put("msg",userId);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        map.put("list",list);

        String path = System.getProperty("user.dir");
        File file = new File(path,userId+".html");
        FileWriter writer = new FileWriter(file);

        template.process(map,writer);

        writer.close();

        return file.getAbsolutePath();

    }

    @GetMapping("/getJson")
    @ResponseBody
    public String getJson(){
        return "data";
    }
}
