package com.acoderx.demo.jdk.concurrent.condition;

/**
 * Created by xudi on 2018/7/4.
 */
public class SignalDemo {
    private final Object o = new Object();
    public static void main(String[] args){
        SignalDemo demo = new SignalDemo();
        //使用wait和notify之前必须获取相应对象的锁
        new Thread(() -> {
            synchronized (demo.o) {
                try {
                    System.out.println("等待信号量");
                    demo.waitSignal();
                    System.out.println("取到信号量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (demo.o) {
                try {
                    Thread.sleep(1000);
                    System.out.println("发送信号量");
                    demo.sendSignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void waitSignal() throws InterruptedException {
        //会释放当前占用的锁，进入休眠状态
        o.wait();
    }

    private void sendSignal() {
        o.notifyAll();
    }
}
