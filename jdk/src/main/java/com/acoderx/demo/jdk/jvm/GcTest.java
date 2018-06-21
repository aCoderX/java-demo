package com.acoderx.demo.jdk.jvm;

import com.acoderx.demo.frame.domain.User;

/**
 * Created by xudi on 2018/6/7.
 */
public class GcTest {
    public static void main(String[] args) throws InterruptedException {
        //-XX:+PrintGCDetails   打印GC日志
        for (int i=0;i<10;i++){
            new Thread(() ->{
                new User();
            }).start();
            Thread.sleep(5000);
            System.gc();
        }
        Thread.sleep(5000000);
    }
}
