package com.acoderx.demo.jdk.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xudi on 2018/7/4.
 * Lock 锁接口
 *  ReentrantLock 可重入锁实现类
 * ReadWriteLock 读写锁接口
 *  ReentrantReadWriteLock 读写锁实现，包含一个读锁一个写锁
 */
public class LockDemo {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args){
        LockDemo lockDemo = new LockDemo();
        //lock方法：如果阻塞会一直等待
        new Thread(lockDemo::testLock).start();
        new Thread(lockDemo::testLock).start();

        //trylock:尝试获取锁，如果获取成功返回true，如果获取失败直接返回false
        new Thread(lockDemo::testTrylock).start();
        new Thread(lockDemo::testTrylock).start();

        //trylock(time):尝试获取锁，如果获取成功返回true，如果返回失败等待time后返回false
        new Thread(()->{
            try {
                lockDemo.testTrylockTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                lockDemo.testTrylockTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //lockInterruptibly：尝试获取锁，如果未获取成功阻塞，可被中断
        Thread t1 = new Thread(() -> {
            try {
                lockDemo.testLockInterrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                lockDemo.testLockInterrupt();
            } catch (InterruptedException e) {
                System.out.println("中断");
                e.printStackTrace();
            }
        });
        t2.start();
        t2.interrupt();

    }

    private void testLock(){
        lock.lock();
        try {
            System.out.println("获取锁");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放锁");
            lock.unlock();
        }
    }

    private void testTrylock(){
        if(lock.tryLock()){
            try {
                System.out.println("获取锁");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("释放锁");
                lock.unlock();
            }
        }else {
            System.out.println("获取锁失败");
        }
    }

    private void testTrylockTime() throws InterruptedException {
        if(lock.tryLock(2, TimeUnit.SECONDS)){
            try {
                System.out.println("获取锁");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("释放锁");
                lock.unlock();
            }
        }else {
            System.out.println("获取锁失败");
        }
    }

    private void testLockInterrupt() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println("获取锁");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放锁");
            lock.unlock();
        }
    }
}
