package com.acoderx.demo.jdk.concurrent.thread;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-08-27
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal threadLocal = new ThreadLocal();

        /**
         * inheritableThreadLocal 会将所有ThreadLocalMap 换成使用inheritableThreadLocals
         * inheritableThreadLocals会在创建线程时(new Thread())存下所有当前线程的threadLocal的value
         */
        ThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

        threadLocal.set("test");
        inheritableThreadLocal.set("test");

        Thread thread = new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });

        thread.start();
        thread.join();
    }
}
