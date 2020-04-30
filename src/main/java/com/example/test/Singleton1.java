package com.example.test;


//饿汉单例模式
public class Singleton1 {

    private static final Singleton1 singleton1 = new Singleton1();

    //构建构造方法
    public  Singleton1(){}

    public static Singleton1 getInstance1(){
        return singleton1;
    }
}
