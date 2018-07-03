package com.acoderx.demo.jdk.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xudi on 17-4-20.
 */
public class Atomic {
    private static AtomicInteger num=new AtomicInteger(0);
    private static long stime = System.currentTimeMillis();
    public static void main(String args[]){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<30;i++){
            service.execute(()->{
                int count = 1;
                while (count<1000000){
                    count++;
                    next();
                }
                next();
                //System.out.println(next());
            });
        }
        service.shutdown();
    }
    public static int next(){
        int res = num.incrementAndGet();
        if(res==30000000){
            System.out.println("time"+(System.currentTimeMillis()-stime));
        }
        return res;
    }
}
