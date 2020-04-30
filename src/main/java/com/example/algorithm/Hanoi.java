package com.example.algorithm;

import java.util.Scanner;

public class Hanoi {
    /**
     * 汉诺塔问题是一个古典的数学问题，内容是:汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。
     * 大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
     * 大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。并且规定，在小圆盘上不能放大圆盘，
     * 在三根柱子之间一次只能移动一个圆盘。
     */

//    private static void move(char x,char y){
//        System.out.printf("%c-->%c",x,y);
//        System.out.print("\n");
//    }
//    private static void hanoit(int n,char one,char two,char three){     //将n个盘子从第一座借助第二座移到第三座
//        if(n==1){                           //如果只有一个盘子
//            move(one,three);
//        }
//        else{
//            hanoit(n-1,one,three,two);      //将一上的盘子借助三移到二上
//            move(one,three);
//            hanoit(n-1,two,one,three);      //将二上的盘子借助一移到三上
//        }
//    }

    private static void move(char x,char y){
        System.out.printf("%c-->%c",x,y);
        System.out.print("\n");
    }

    private static void hanoit(int n,char one,char two,char three){  //将n个盘子从第一座借助第二座移到第三座
        if(n==1){            //如果只有一个盘子
            move(one,three);
        }else{
            hanoit(n-1,one,three,two); //将一上的盘子借助三移到二上
            move(one,three);
            hanoit(n-1,two,one,three);
        }
    }

    public static void main(String[] args) {
        int m;
        System.out.println("请输入你要移动的盘子数：");
        Scanner s=new Scanner(System.in);
        m=s.nextInt();
        System.out.println("移动"+m+"个盘子的步骤如下");
        hanoit(m,'A','B','C');
    }
}
