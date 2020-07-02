package com.example.handler;

import com.example.utils.AlgorithmUtils;
import com.example.vo.Case;
import com.example.vo.User;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务Handler示例（Bean模式）
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * 5、任务执行结果枚举：SUCCESS、FAIL、FAIL_TIMEOUT
 */
@JobHandler(value = "testJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("XXL-JOB, testJobHandler.");
        System.out.println("XXL-JOB测试");
        List<User> userList = new ArrayList<>();
        User user1 =new User();
        user1.setUserId("101");
        user1.setUserName("lll");
        user1.setNum("2");

        User user2 =new User();
        user2.setUserId("102");
        user2.setUserName("lll");
        user2.setNum("5");

        User user3 =new User();
        user3.setUserId("103");
        user3.setUserName("lll");
        user3.setNum("1");

        User user4 =new User();
        user4.setUserId("104");
        user4.setUserName("lll");
        user4.setNum("3");

        User user5 =new User();
        user5.setUserId("105");
        user5.setUserName("lll");
        user5.setNum("2");

        User user6 =new User();
        user6.setUserId("106");
        user6.setUserName("lll");
        user6.setNum("2");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        List<Case> caseList = new ArrayList<>();
        Case case1 = new Case();
        case1.setCode("1");
        caseList.add(case1);

        Case case2 = new Case();
        case2.setCode("2");
        caseList.add(case2);

        Case case3 = new Case();
        case3.setCode("3");
        caseList.add(case3);

        Case case4 = new Case();
        case4.setCode("4");
        caseList.add(case4);

        Case case5 = new Case();
        case5.setCode("5");
        caseList.add(case5);

        Case case6 = new Case();
        case6.setCode("6");
        caseList.add(case6);

        Case case7 = new Case();
        case7.setCode("7");
        caseList.add(case7);

        Case case8 = new Case();
        case8.setCode("8");
        caseList.add(case8);

        Case case9 = new Case();
        case9.setCode("9");
        caseList.add(case9);

        Case case10 = new Case();
        case10.setCode("10");
        caseList.add(case10);


        Case case11 = new Case();
        case11.setCode("11");
        caseList.add(case11);


        Case case12 = new Case();
        case12.setCode("12");
        caseList.add(case12);

        Case case13 = new Case();
        case13.setCode("13");
        caseList.add(case13);

        Case case14 = new Case();
        case14.setCode("14");
        caseList.add(case14);

        Case case15 = new Case();
        case15.setCode("15");
        caseList.add(case15);

        Case case16 = new Case();
        case16.setCode("16");
        caseList.add(case16);

        Case case17 = new Case();
        case17.setCode("17");
        caseList.add(case17);

        Case case18 = new Case();
        case18.setCode("18");
        caseList.add(case18);

        Case case19 = new Case();
        case19.setCode("19");
        caseList.add(case19);

        //未分配的案件数
        int task_num = caseList.size();
        //律师人员总数
        int rev_num = userList.size();
        String[][] rev_task =new String[rev_num][3];
        System.out.println("算法前的任务分配：");
        for(int i=0;i<rev_num;i++){
            rev_task[i][0] = userList.get(i).getUserId();
            rev_task[i][1]= userList.get(i).getNum();
            rev_task[i][2] = "0";
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+"|");
        }

        System.out.println();

        //调用算法工具类
        AlgorithmUtils.taskAllocation(task_num, rev_num, rev_task);
        List<Case> deleList = new ArrayList<>();
        deleList.clear();
        System.out.println("算法后的任务分配：");
        for(int i=0;i<rev_num;i++){
            System.out.print(rev_task[i][0]+","+rev_task[i][1]+","+rev_task[i][2]+"|");
            //获取律师ID
            String userId = rev_task[i][0];
            //获取需要分配的件数
            String num = rev_task[i][2];
            int a = Integer.valueOf(num);
            for(User user:userList){
                if(user.getUserId() == userId){
                    for (int j=0;j<a;j++){
                        Case cases = caseList.get(j);
                        cases.setUserId(userId);
                        deleList.add(caseList.get(j));
                    }
                    System.out.println(deleList);
                    caseList.removeAll(deleList);
                    deleList.clear();
                }
            }
            System.out.println(caseList);
        }
        return SUCCESS;
    }

}

