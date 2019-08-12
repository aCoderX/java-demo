package com.acoderx.demo.commons.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Description:限流
 *
 * @author xudi
 * @since 2019-07-12
 */
public class LimiterDemo {
    /**
     * 限制每秒有两个令牌
     */
    private static RateLimiter limiter = RateLimiter.create(10);
    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            limiter.acquire();
            System.out.println(i);
        }
    }
}
