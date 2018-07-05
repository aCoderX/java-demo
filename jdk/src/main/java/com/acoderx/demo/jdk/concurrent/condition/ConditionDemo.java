package com.acoderx.demo.jdk.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xudi on 2018/7/4.
 */
public class ConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public static void main(String[] args){
        ConditionDemo demo = new ConditionDemo();
        new Thread(()->{
            try {
                demo.testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                demo.sendSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void testLock() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("获取锁成功");
            System.out.println("等待信号");
            //线程将释放锁，并且将自己沉睡，等待唤醒
            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("获取信号：主动释放锁成功");
            lock.unlock();
        }
    }
    private void sendSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("获取锁成功");
            condition.signalAll();
            System.out.println("发送信号");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("发送信号：主动释放锁成功");
        }
    }
}
