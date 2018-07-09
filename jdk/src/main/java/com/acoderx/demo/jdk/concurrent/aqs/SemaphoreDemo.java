package com.acoderx.demo.jdk.concurrent.aqs;

import java.util.concurrent.Semaphore;

/**
 * Created by xudi on 2018/7/9.
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args){
        new Thread(SemaphoreDemo::doSomething).start();
        new Thread(SemaphoreDemo::doSomething).start();
        new Thread(SemaphoreDemo::doSomething).start();
    }

    private static void doSomething(){
        try {
            semaphore.acquire();
            System.out.println("开始...");
            Thread.sleep(2000);
            semaphore.release();
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
