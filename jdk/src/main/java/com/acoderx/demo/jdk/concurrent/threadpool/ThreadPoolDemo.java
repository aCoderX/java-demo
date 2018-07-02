package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by xudi on 2018/7/2.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int poolSize = 1;//最小活跃线程
        int maxSize = 1;//最大线程数
        int keepAliveTime = 10;//大于poolSize小于max的线程在空闲后等待的时间
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1);//任务队列,有界阻塞队列
        ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        queue.add(() -> {
            System.out.println("haha");
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize,maxSize,keepAliveTime, TimeUnit.SECONDS,queue,threadFactory,rejectedExecutionHandler);
        threadPoolExecutor.execute(()->{
            try {
                System.out.println(Thread.currentThread().getId());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1");
        });
        Future future = threadPoolExecutor.submit(() -> {
            return "fff";
        });
        System.out.println(future.get());
        //会被拒绝
        threadPoolExecutor.execute(()->{
            System.out.println(Thread.currentThread().getId());
            System.out.println("拒绝");
        });
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
