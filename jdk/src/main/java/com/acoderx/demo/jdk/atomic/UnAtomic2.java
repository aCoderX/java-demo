package com.acoderx.demo.jdk.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 17-4-13.
 */
public class UnAtomic2 {
    private static int num=0;
    public static void main(String args[]){
        ExecutorService services = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            services.execute(()->{
                int count = 1;
                while (count<1000){
                    count++;
                    next();
                }
                System.out.println(next());
            });
        }
        services.shutdown();
    }
    public static int next(){
        num=num+1;
        return num;
    }

    public synchronized static int nextSave(){
        num=num+1;
        return num;
    }

}
