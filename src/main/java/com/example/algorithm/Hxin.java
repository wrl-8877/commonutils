package com.example.algorithm;

public class Hxin {
    /*
     * 韩信带兵不足百人，3人一行排列多一人，7人一行排列少两人，5人一行正好，本例是计算韩信究竟点了多少兵？
     */
    public static void main(String[] args) {

        for(int i=0;i<100;i++){
           int a = i%3;
           int b = i%7;
           int c = i%5;
           if(a==1&&b==5&&c==0){
               System.out.println("韩信带的兵数是："+i);
           }
        }
    }
}
