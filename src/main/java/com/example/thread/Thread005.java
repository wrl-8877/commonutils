package com.example.thread;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技
 * @since 2020/6/17
 */
public class Thread005 extends Thread{

    private volatile static  boolean flag = true ;

    @Override
    public void run() {
        while(flag){
        }
       System.out.println("子线程结束---------");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread005 thread005 = new Thread005();
        thread005.start();
        Thread.sleep(100);
        flag = false;
    }
}
