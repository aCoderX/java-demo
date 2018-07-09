package com.acoderx.demo.jdk.concurrent.aqs;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xudi on 2018/7/8.
 * 循环栅栏，所有线程相互等待
 * 可被重置
 * 由lock和condition实现
 */
public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        System.out.println("优先执行");
    });

    public static void main(String[] args){
        new Thread(CyclicBarrierDemo::doSomething).start();
        new Thread(CyclicBarrierDemo::doSomething).start();
        new Thread(CyclicBarrierDemo::doSomething).start();
    }

    public static void doSomething(){
        try {
            int sleepTime = new Random().nextInt(3000);
            System.out.println(Thread.currentThread().getId() + "准备" + sleepTime);
            System.out.println(Thread.currentThread().getId()+"等待其他");
            Thread.sleep(sleepTime);
            cyclicBarrier.await();
            System.out.println("run--"+Thread.currentThread().getId());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
