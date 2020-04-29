package com.example.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AlgorithmUtils {

    public static void taskAllocation(int task_num, int rev_num, String[][] rev_task) {

        Random rd = new Random();
        List<Integer> rdList = new ArrayList<>();
        int temp;

        //获得审核人员中的最大未完成任务数
        int max_task = Integer.parseInt(rev_task[0][1]);
        for(int i = 1; i < rev_num; i++){
            if(max_task < Integer.parseInt(rev_task[i][1]))
                max_task = Integer.parseInt(rev_task[i][1]);
        }

        //以最大待审核任务数为标杆，判断第一轮可容纳的任务数
        int ava_task = 0;
        List<Integer> lower_List = new ArrayList<>();
        for(int i=0;i<rev_num;i++){
            if((max_task-Integer.parseInt(rev_task[i][1])) > 0){
                ava_task += (max_task-Integer.parseInt(rev_task[i][1]));
                lower_List.add(i);
            }
        }

        int task_rest;
        int task_avg;
        //第一种情况：第一轮可容纳的任务数小于待分配的任务数
        if(ava_task - task_num <= 0) {
            for(int i = 0; i < rev_num; i++) {
                rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
            }
            task_rest = task_num-ava_task;
            task_avg = task_rest/rev_num;
            if(task_rest != 0) {
                while(task_avg > 0) {
                    for(int i = 0; i < rev_num; i++) {
                        rev_task[i][2] = String.valueOf(Integer.parseInt(rev_task[i][2])+task_avg);
                    }
                    task_rest -= rev_num*task_avg;
                    task_avg = task_rest/rev_num;
                }
                rdList.removeAll(rdList);
                while(rdList.size() < (task_rest+1)){
                    temp = rd.nextInt(rev_num);
                    if(!rdList.contains(temp)){
                        rdList.add(temp);
                    }
                }
                for(int i = 0; i < task_rest; i++) {
                    rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                }
            }
        }else {//第二种情况：第一轮可容纳的任务数大于待分配的任务数，此时降低一个单位的标杆(max_task-1)，然后循环计算可容纳的任务数，直到退出循环
            while(ava_task - task_num > lower_List.size()) {
                max_task--;
                ava_task = 0;
                lower_List.removeAll(lower_List);
                for(int i=0;i<rev_num;i++){
                    rev_task[i][2] = "0";
                    if((max_task-Integer.parseInt(rev_task[i][1])) > 0){
                        rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
                        ava_task += Integer.parseInt(rev_task[i][2]);
                        lower_List.add(i);
                    }
                }
            }
            if(ava_task - task_num > 0) {//如果可容纳的任务数大于待分配的任务数，那么需要再再降低一个单位的标杆
                max_task--;
                ava_task = 0;
                lower_List.removeAll(lower_List);
                for(int i=0;i<rev_num;i++){
                    if((max_task-Integer.parseInt(rev_task[i][1])) >= 0){
                        rev_task[i][2] = String.valueOf(max_task-Integer.parseInt(rev_task[i][1]));
                        ava_task += Integer.parseInt(rev_task[i][2]);
                        lower_List.add(i);
                    }
                }
                task_rest = task_num - ava_task;
                rdList.removeAll(rdList);
                while(rdList.size() < (task_rest+1)){
                    temp = rd.nextInt(rev_num);
                    if((!rdList.contains(temp))&&(lower_List.contains(temp))){
                        rdList.add(temp);
                    }
                }
                for(int i = 0; i < task_rest; i++) {
                    rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                }
            }else {
                task_rest = task_num-ava_task;
                if(task_rest != 0) {
                    rdList.removeAll(rdList);
                    while(rdList.size() < (task_rest+1)){
                        temp = rd.nextInt(rev_num);
                        if((!rdList.contains(temp))&&(lower_List.contains(temp))){
                            rdList.add(temp);
                        }
                    }
                    for(int i = 0; i < task_rest; i++) {
                        rev_task[rdList.get(i)][2] = String.valueOf(Integer.parseInt(rev_task[rdList.get(i)][2])+1);
                    }
                }
            }
        }

        //记录被分配的任务数
        for(int i=0;i<rev_num;i++){
            rev_task[i][1] = String.valueOf(Integer.parseInt(rev_task[i][1])+Integer.parseInt(rev_task[i][2]));
        }
    }
}

