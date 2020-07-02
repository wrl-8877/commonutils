package com.example.thread;

import java.util.concurrent.*;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技
 * @since 2020/6/15
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+ "正在异步调用接口发送短信");
        try{
            Thread.sleep(100);
        }catch (Exception e){

        }
        return "短信发送成功";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(Thread.currentThread().getName() + result);
    }

}
