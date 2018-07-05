package com.acoderx.demo.jdk.concurrent.condition;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by xudi on 2018/7/4.
 */
public class LockSupportDemo {
    public static void main(String[] args){
        Thread w = new Thread(()->{
            System.out.println("part w");
            LockSupport.park(Thread.currentThread());
            System.out.println("unpark w");
        });
        w.start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                LockSupport.unpark(w);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
