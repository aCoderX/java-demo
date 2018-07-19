package com.acoderx.demo.jdk.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by xudi on 2018/7/19.
 * 死锁检测
 */
public class DeadlockCheckDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();


    public static void main(String[] args) throws InterruptedException {
        new Thread(DeadlockCheckDemo::lock1).start();
        new Thread(DeadlockCheckDemo::lock2).start();

        Thread.sleep(2000);

        //死锁检测
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreadIds != null) {
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreadIds);
            for (ThreadInfo threadInfo : threadInfos) {
                System.out.println(threadInfo.toString());
            }
        }
    }

    private static void lock1() {
        synchronized (lock1) {
            System.out.println("Thread-" + Thread.currentThread().getId() + "获取lock1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("Thread-" + Thread.currentThread().getId() + "获取lock2");
            }
        }
    }

    private static void lock2() {
        synchronized (lock2) {
            System.out.println("Thread-" + Thread.currentThread().getId() + "获取lock2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("Thread-" + Thread.currentThread().getId() + "获取lock1");
            }
        }
    }
}
