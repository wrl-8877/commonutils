package com.example.test;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技
 * @since 2020/6/9
 */
public class Test {

    public static void main(String[] args) {
        char data[] = {'a', 'b', 'c'};
        String str = new String(data);
        System.out.println();

        int num =3;

        switch(num){
            case 1:
                System.out.println("11");
                break;
            case 2:
                System.out.println("22");
                break;
            case 3:
                System.out.println("33");
                break;
            default:
                System.out.println("44");
                break;
        }




    }
}
