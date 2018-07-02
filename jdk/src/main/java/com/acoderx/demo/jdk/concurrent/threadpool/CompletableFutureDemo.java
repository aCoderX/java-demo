package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by xudi on 2018/7/2.
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("ss");
        });
        CompletableFuture<String> futureRes = CompletableFuture.supplyAsync(() -> {
            return "11";
        });
        futureRes.whenComplete((a,b)->{
            System.out.println(a);
            System.out.println(b);
        });
        //将上一个future的运行结果作为入参再次创建future
        CompletableFuture futureApply = futureRes.thenApply((s)->{
            return Integer.parseInt(s);
        });
        System.out.println(futureApply.get());
    }
}
