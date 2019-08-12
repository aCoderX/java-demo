package com.acoderx.demo.jdk.java9.reactive;

import java.util.concurrent.Flow;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-08-12
 */
public class SubscriberDemo implements Flow.Subscriber<Integer> {
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("subscribe");
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("消费"+item);
        //消费完之后 继续获取新的
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("错误");
    }

    @Override
    public void onComplete() {
        System.out.println("完成");
    }
}
