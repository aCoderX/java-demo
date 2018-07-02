package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 2018/7/2.
 *
 * 线程池
 * Executor 任务执行器接口，定义了execute方法
 *  ExecutorService 任务执行接口，定义了一些执行、中断，批量执行方法
 *      AbstractExecutorService ExecutorService的抽象类
 *          ThreadPoolExecutor 线程池的实现
 *          ForkJoinPool 任务拆分线程池
 *      ScheduledExecutorService 延迟、定时执行任务接口
 *              ScheduledThreadPoolExecutor 线程池
 *
 *  CompletionService 完成服务，类似于ExecutorService，可以更好的获取执行的结果
 *      ExecutorCompletionService CompletionService的实现
 * RejectedExecutionHandler 线程池拒绝策略
 * Executors 线程池工厂
 *
 * Future 异步计算的结果接口，定义了获取、取消方法
 *  RunnableFuture 可运行的异步计算接口，Future继承了Runnable
 *      FutureTask Future的基础实现类
 *  ForkJoinTask 交给ForkJoinPool运行的任务抽象类
 *      RecursiveAction 无结果的任务
 *      RecursiveTask 有结果的任务
 *      CountedCompleter
 *  ScheduledFuture 交给ScheduledExecutorService执行的延迟、定时任务接口
 *      RunnableScheduledFuture
 *  CompletableFuture
 * CompletionStage
 * ForkJoinWorkerThread 用于ForkJoinPool的线程
 *
 */
public class Main {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(()->{
            System.out.println("test");
        });
    }
}
