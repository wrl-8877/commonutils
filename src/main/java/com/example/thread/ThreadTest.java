package com.example.thread;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1("t1");
        Thread1 thread2 = new Thread1("t2");
        Thread1 thread3 = new Thread1("t3");
        thread1.start();
        // thread1.join(10);
        thread2.start();
        // thread2.join(10);
        thread3.start();

        thread3.wait();

        Thread.sleep(3000);
    }
}
