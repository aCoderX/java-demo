package com.acoderx.demo.jdk.java9.reactive;

import java.util.concurrent.SubmissionPublisher;

/**
 * Description:reactive stream ，spring flux基础
 *
 * @author xudi
 * @since 2019-08-12
 */
public class PublishDemo {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        SubscriberDemo demo = new SubscriberDemo();
        publisher.subscribe(demo);
        for (int i = 0; i < 1000; i++) {
            System.out.println("生产"+i);
            publisher.submit(i);
        }

        Thread.currentThread().join(10000);
        publisher.close();
    }
}
