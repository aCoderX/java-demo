package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by xudi on 2018/7/2.
 */
public class CompletionDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        CompletionService<String> completionService = new ExecutorCompletionService<>(service);
        completionService.submit(()->{
            Thread.sleep(1000);
            return "1";
        });
        System.out.println(completionService.take().get());
        service.shutdown();
    }
}
