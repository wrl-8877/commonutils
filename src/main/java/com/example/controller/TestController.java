package com.example.controller;


import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping
    public String returnT(){
        return "SUCCESS";
    }

    @GetMapping("/{userName}")
    public String return1(@PathVariable(value = "userName") String userName){
       return "userName"+userName;
    }

    @GetMapping("/")
    public  String return2(@RequestParam(value = "userName") String userName){
        return "userName"+userName;
    }
}
