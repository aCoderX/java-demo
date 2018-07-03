package com.acoderx.demo.jdk.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 17-4-13.
 */
public class UnAtomic2 {
    private static int num=0;
    private static long stime = System.currentTimeMillis();
    public static void main(String args[]){
        ExecutorService services = Executors.newCachedThreadPool();
        for(int i=0;i<30;i++){
            services.execute(()->{
                int count = 1;
                while (count<1000000){
                    count++;
                    nextSave();
                }
                nextSave();
                //System.out.println(nextSave());
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
        if(num==30000000){
            System.out.println("time"+(System.currentTimeMillis()-stime));
        }
        return num;
    }

}
