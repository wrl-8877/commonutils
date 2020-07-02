package com.example.thread;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技
 * @since 2020/6/15
 */
public class Thread001 extends Thread{

    @Override
    public void run() {
        System.out.println("我是子线程:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("我是主线程:" + Thread.currentThread().getName());
        Thread001 thread001 = new Thread001();
        thread001.start();
    }
}
