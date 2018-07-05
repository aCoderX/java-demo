package com.acoderx.demo.jdk.concurrent.collection;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by xudi on 2018/7/5.
 */
public class TransferQueueDemo {
    public static void main(String[] args){
        TransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(queue.hasWaitingConsumer());
                System.out.println(queue.getWaitingConsumerCount());
                queue.transfer("value");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
