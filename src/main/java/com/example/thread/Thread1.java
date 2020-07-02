package com.example.thread;

public class Thread1 extends Thread{


    public Thread1(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<200;i++){
            System.out.println(this.getName()+" "+i);
        }
    }
}
