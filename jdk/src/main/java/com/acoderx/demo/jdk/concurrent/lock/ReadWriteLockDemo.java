package com.acoderx.demo.jdk.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by xudi on 2018/7/4.
 */
public class ReadWriteLockDemo {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args){
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        //获取读锁
        new Thread(demo::readLock).start();
        new Thread(demo::readLock).start();

        //获取写锁
        new Thread(demo::writeLock).start();
        new Thread(demo::writeLock).start();
    }
    private void readLock(){
        lock.readLock().lock();
        try {
            System.out.println("获取锁成功");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁");
            lock.readLock().unlock();
        }
    }

    private void writeLock(){
        lock.writeLock().lock();
        try {
            System.out.println("获取锁成功");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁");
            lock.writeLock().unlock();
        }
    }
}
