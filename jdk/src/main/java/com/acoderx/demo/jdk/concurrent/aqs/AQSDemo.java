package com.acoderx.demo.jdk.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by xudi on 2018/7/6.
 */
public class AQSDemo{
    private static Sync sync = new Sync();
    private static ShareSync shareSync = new ShareSync(1);
    //独占锁
    private static final class Sync extends AbstractQueuedSynchronizer{
        /**
         *
         * @param arg
         * @return 是否占用成功
         */
        @Override
        protected boolean tryAcquire(int arg) {
            final Thread current = Thread.currentThread();
            if (getState() == 0) {
                if(compareAndSetState(0,arg)){
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else {
                if (current == getExclusiveOwnerThread()) {
                    setState(getState() + arg);
                    return true;
                }
            }
            return false;
        }

        /**
         *
         * @param arg
         * @return 是否有线程可以Acquire
         */
        @Override
        protected boolean tryRelease(int arg) {
            if(Thread.currentThread()!=getExclusiveOwnerThread()){
                throw new IllegalMonitorStateException();
            }
            int c = getState() - arg;
            setState(c);
            if(c==0){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }
    }

    //共享锁
    private static final class ShareSync extends AbstractQueuedSynchronizer{
        private int size;

        public ShareSync(int size) {
            this.size = size;
        }

        /**
         *
         * @param arg
         * @return 小于0:失败，等于0：本次成功，后续不能成功，大于0：成功
         */
        @Override
        protected int tryAcquireShared(int arg) {
            int c = getState() + arg;
            if (size>=c) {
                setState(c);
                return 1;
            }else{
                return -1;
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            int c = getState() - arg;
            if(c>=0){
                setState(c);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
//        testSync();
        testShare();
    }

    /**
     * 测试独占锁
     */
    private static void testSync(){
        new Thread(() -> {
            sync.acquire(1);
            System.out.println(Thread.currentThread().getId() + "获取锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "--done");
            sync.release(1);
        }).start();

        new Thread(() -> {
            sync.acquire(1);
            System.out.println(Thread.currentThread().getId() + "获取锁");
            System.out.println(Thread.currentThread().getId() + "--done");
            sync.release(1);
        }).start();
    }

    /**
     * 测试共享锁
     */
    private static void testShare(){
        new Thread(() -> {
            shareSync.acquireShared(1);
            System.out.println("开始...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "--done");
            shareSync.releaseShared(1);
        }).start();

        new Thread(() -> {
            shareSync.acquireShared(1);
            System.out.println("开始...");
            System.out.println(Thread.currentThread().getId() + "--done");
            shareSync.releaseShared(1);
        }).start();
    }



}
