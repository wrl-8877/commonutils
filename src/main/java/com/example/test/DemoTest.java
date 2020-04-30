package com.example.test;

import com.example.vo.Case;
import com.example.vo.WCase;
import com.example.vo.YUser;

import java.util.ArrayList;
import java.util.List;

public class DemoTest {
    public static void main(String[] args) {

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

        List<WCase> wCaseList = new ArrayList<>();
        WCase wCase = new WCase();
        wCase.setUserId("101");
        wCase.setUserName("101");
        wCase.setNum("2");
        WCase wCase2 = new WCase();
        wCase2.setUserId("102");
        wCase2.setUserName("102");
        wCase2.setNum("4");
        WCase wCase3 = new WCase();
        wCase3.setUserId("103");
        wCase3.setUserName("103");
        wCase3.setNum("3");

        wCaseList.add(wCase);
        wCaseList.add(wCase2);
        wCaseList.add(wCase3);

        List<YUser> yUserList = new ArrayList<>();

        YUser yUser1 = new YUser();
        yUser1.setUserId("101");
        yUser1.setUserName("101");

        YUser yUser2 = new YUser();
        yUser2.setUserId("102");
        yUser2.setUserName("102");

        YUser yUser3 = new YUser();
        yUser3.setUserId("103");
        yUser3.setUserName("103");

        YUser yUser4 = new YUser();
        yUser4.setUserId("104");
        yUser4.setUserName("104");

        YUser yUser5 = new YUser();
        yUser5.setUserId("105");
        yUser5.setUserName("105");

        yUserList.add(yUser1);
        yUserList.add(yUser2);
        yUserList.add(yUser3);
        yUserList.add(yUser4);
        yUserList.add(yUser5);

        List<YUser> yUserList1 = new ArrayList<>();
        for(YUser yUser:yUserList){
            for(WCase wCase1:wCaseList){
                if(yUser.getUserId() == wCase1.getUserId()){
                    yUserList1.add(yUser);
                    break;
                }
            }
        }
        System.out.println(yUserList1);
        yUserList.removeAll(yUserList1);
        for(YUser yUser:yUserList){
            WCase wCase0 = new WCase();
            wCase0.setUserId(yUser.getUserId());
            wCase0.setUserName(yUser.getUserName());
            wCase0.setNum("0");
            wCaseList.add(wCase0);
        }


        System.out.println(wCaseList);

    }
}
