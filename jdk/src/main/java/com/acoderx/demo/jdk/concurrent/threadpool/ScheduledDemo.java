package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by xudi on 2018/7/2.
 */
public class ScheduledDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<String> future = scheduledExecutorService.schedule(() -> {
            System.out.println("yes");
            return "s";
        }, 5, TimeUnit.SECONDS);
        System.out.println(future.get());
        scheduledExecutorService.shutdown();
    }
}
