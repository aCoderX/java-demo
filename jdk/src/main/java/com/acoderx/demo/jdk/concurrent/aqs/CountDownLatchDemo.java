package com.acoderx.demo.jdk.concurrent.aqs;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xudi on 2018/7/8.
 * 闭锁，允许一个线程或多个线程等待其他线程
 * 只能等待一次，不可被重置
 * 由AQS实现
 */
public class CountDownLatchDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args){
        new Thread(()->{
            test();
        }).start();

        new Thread(()->{
            test();
        }).start();


        startApp();

    }

    private static void startApp(){
        try {
            System.out.println(Thread.currentThread().getId()+"等待其他线程");
            countDownLatch.await();
            System.out.println("其他线程完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void test(){
        try {
            int sleepTime = new Random().nextInt(3000);
            System.out.println(Thread.currentThread().getId() + "准备" + sleepTime);
            Thread.sleep(sleepTime);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
