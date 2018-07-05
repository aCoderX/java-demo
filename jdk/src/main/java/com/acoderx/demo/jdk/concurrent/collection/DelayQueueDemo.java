package com.acoderx.demo.jdk.concurrent.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by xudi on 2018/7/5.
 */
public class DelayQueueDemo {
    public static void main(String[] args){
        //延迟队列
        DelayQueue<TestDelay> delayQueue = new DelayQueue<>();
        new Thread(()->{
            delayQueue.put(new TestDelay(2,TimeUnit.SECONDS,"哈哈"));
            try {
                System.out.println("获取数据");
                System.out.println(delayQueue.take().getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    static class TestDelay implements Delayed {
        private long time;
        private TimeUnit unit;
        private String value;

        public TestDelay(int time,TimeUnit unit,String value) {
            this.time = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time, unit);
            this.unit = unit;
            this.value = value;
        }

        @Override
        public long getDelay(TimeUnit u) {
            long diff = time - System.currentTimeMillis();
            return u.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            long diff = getDelay(NANOSECONDS) - other.getDelay(NANOSECONDS);
            return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
        }

        public String getValue() {
            return value;
        }
    }
}
