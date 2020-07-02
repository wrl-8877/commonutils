package com.example.test;

import com.example.utils.Base64Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Demo {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
//        User user1 = new User();
//        user1.setUserName("1234");
//        user1.setPassword("1234");
//        user1.setSex("1");
//        User user2 = new User();
//        user2.setUserName("1234");
//        user2.setPassword("1234");
//        user2.setSex("0");
//        User user3 = new User();
//        user3.setUserName("1234");
//        user3.setPassword("1234");
//        user3.setSex("0");
//        User user4 = new User();
//        user4.setUserName("1234");
//        user4.setPassword("12341");
//        user4.setSex("1");
//        User user5 = new User();
//        user5.setUserName("12345");
//        user5.setPassword("1234");
//        user5.setSex("1");
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//        list.add(user4);
//        list.add(user5);
        list.forEach(o->
                System.out.println("12244:"+o.toString())
        );
        List<User> distinctClass = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUserName() + ";" + o.getPassword()))), ArrayList::new));
        distinctClass.forEach(o->
                System.out.println(o.toString()));
    }
}
