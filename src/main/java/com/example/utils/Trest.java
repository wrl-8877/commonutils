package com.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Trest {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入任务数：");
        int task_num = sc.nextInt();
        System.out.println("请输入审核人员的当前未完成任务数组，整数数字输入时用英文逗号隔开：");
        String inputString=sc.next().toString();
        String stringArray[]=inputString.split(",");

        int rev_num = stringArray.length;//审核人员总数
        //
        String[][] rev_task =new String[rev_num][3];
        Random rd = new Random();
        List<Integer> rdList = new ArrayList<>();
        rdList.removeAll(rdList);
        int temp;
        while(rdList.size() < (rev_num+1)){
            temp = rd.nextInt(100);
            if(!rdList.contains(temp)){
                rdList.add(temp);
            }
        }

        System.out.println("算法前的任务分配：");
        for(int i=0;i<rev_num;i++){
            //第i个审核员ID
            rev_task[i][0] = String.valueOf(rdList.get(i) + 1);
            //第i个审核员当前未完成的任务数
            rev_task[i][1]= stringArray[i];
            // 第i个审核员应当被分配的任务数
            rev_task[i][2] = "0";
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+" ");
        }
        System.out.println();

        AlgorithmUtils.taskAllocation(task_num, rev_num, rev_task);//调用算法工具类
        System.out.println("算法后的任务分配：");
        for(int i=0;i<rev_num;i++){
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+" ");
        }
    }
}
