package com.example.algorithm;

public class BQMJ {
    /**
     * 案列说明：主要内容是：公鸡5元一只，母鸡3元一只，小鸡1元三只，问100元怎样可以买100鸡？
     */
    public static void main(String[] args) {
        //初始化啊a,b,c 分别表示公鸡，母鸡，小鸡
         int a ,b ,c = 0;
         //100元最多买20只公鸡，33只母鸡 ，即 a<20 ,b<33
         for(a=0;a<20;a++){
            for(b=0;b<33;b++){
                //小鸡的个数
               c = 100 -a -b ;
               //价钱为100,且小鸡取余数为0即3的倍数
               if(a*5+b*3+c/3 ==100 && c%3 ==0){
                   System.out.print("    可以买公鸡的只数："+a);
                   System.out.print("    可以买母鸡的只数："+b);
                   System.out.print("    可以买小鸡的只数："+c);
                   System.out.println("\n");
               }
            }
         }


    }
}
