package com.example.test;

public class Singleton2 {

    private static  Singleton2 singleton2 = null ;

    public Singleton2(){}

    public synchronized static Singleton2 getSingleton2(){
        if(singleton2 == null){
            singleton2 = new Singleton2();
        }
        return  singleton2;
    }

}
