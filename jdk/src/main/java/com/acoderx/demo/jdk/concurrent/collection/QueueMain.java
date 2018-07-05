package com.acoderx.demo.jdk.concurrent.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xudi on 2018/7/5.
 *
 * BlockingQueue 阻塞队列接口：定义了take、put等阻塞方法，线程安全的队列，基本都使用Lock、condition实现
 *  ArrayBlockingQueue 数组实现的阻塞队列（有界），使用了ReentrantLock，condition来控制阻塞
 *  LinkedBlockingQueue 链表实现的阻塞队列（有界、无界），使用了两个ReentrantLock分别控制读写
 *  DelayQueue 无界延迟队列,内部由PriorityQueue实现，元素一直到过期时间才能被获取到
 *  TransferQueue 用于直接连接两个线程
 *      LinkedTransferQueue 比SynchronousQueue更好用，不仅可以transfer方法也可正常使用队列方法
 *  PriorityBlockingQueue 无界阻塞优先级队列 由二叉堆实现
 *  SynchronousQueue 队列中不存储数据，只是将put的线程阻塞一直到有线程take
 *  BlockingDeque 双端队列接口
 *      LinkedBlockingDeque 链表实现的双端队列
 * ConcurrentLinkedDeque 双端队列，使用CAS
 * ConcurrentLinkedQueue 使用CAS性能比LinkedBlockingQueue好
 *
 */
public class QueueMain {
    public static void main(String[] args){
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("插入");
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
