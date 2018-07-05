package com.acoderx.demo.jdk.concurrent.thread;

/**
 * Created by xudi on 2018/7/5.
 */
public class InterruptDemo {
    public static void main(String[] args){
        Thread t = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().isInterrupted());
                doSomething();
            } catch (InterruptedException e) {
                System.out.println("被中断");
//                System.out.println(Thread.currentThread().isInterrupted());
//                e.printStackTrace();
                Thread.currentThread().interrupt();//重新中断线程，这里没有必要
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t.start();

        new Thread(()->{
            t.interrupt();
        }).start();
    }

    private static void doSomething() throws InterruptedException {
        while (true) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            System.out.println("do something");
        }
    }
}
