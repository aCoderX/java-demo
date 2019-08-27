package com.acoderx.demo.commons.threadlocal.transmittable;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 阿里 transmittable-thread-local 用于runnable的demo
 *
 *
 * InheritableThreadLocal demo 见com.acoderx.demo.jdk.concurrent.thread.InheritableThreadLocalDemo
 *
 * @author xudi
 * @since 2019-08-27
 */
public class RunnableDemo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        /**
         * 在InheritableThreadLocal基础上存储了一层holder
         */
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<>();
        parent.set("value-set-in-parent");

        Runnable task = new Runnable() {
            @Override
            public void run() {
                String value = parent.get();
                System.out.println(value);
            }
        };

// 额外的处理，生成修饰了的对象ttlRunnable
        /**
         * TtlRunnable中在get时会从TransmittableThreadLocal中获取存储的holder（用hashMap存储的InheritableThreadLocal）,
         * 通过去改变holder的内容，来获取value
         */
        Runnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);
        executorService.shutdown();

    }
}
