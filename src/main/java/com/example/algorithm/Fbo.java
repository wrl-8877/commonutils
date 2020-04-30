package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fbo {
    /**
     * 斐波那契数列的定义：它的第一项和第二项均为1，以后各项都为前两项之和
     */
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("请输入你想查看的斐波那契数列个数：");
        int num=s.nextInt();
        System.out.println("你想看的斐波那契数列：");
        d(num);  //list集合
        f(num);  //while循环
    }

    public static void f(int x){
        int f1=1,f2=1,i=3;
        if(x==1)System.out.print(f1);
        if(x==2)System.out.print(f1+"  "+f2);
        if(x>=3){                 //求位置大于三的数列
            System.out.print(f1+"  "+f2);
            while(x>=i){         //求数列
                if(i%2==1){
                    f1=f2+f1;        //求两项之和
                    System.out.print("  "+f1);
                    i++;
                    continue;
                }
                if(i%2==0){
                    f2=f2+f1;
                    System.out.print("  "+f2);
                    i++;
                    continue;
                }

            }
        }
    }

    //list集合是有序的，可以用前两个的集合值的和作为下一个集合的数值
    public static void d(int num){
        int i = 3;
        List<Integer> list = new ArrayList<>();
        if(num == 1){
            list.add(1);
        }
        if(num == 2){
            list.add(1);
            list.add(1);
        }
        if(num>=3){
            list.add(1);
            list.add(1);
            while(num >=i){
                int a =0;
                //list是从0开时计数
                a = list.get(i-2) +list.get(i-3);
                list.add(a);
                i++;
            }
        }
        for(int b:list){
            System.out.println(b + " ");
        }
    }
}
