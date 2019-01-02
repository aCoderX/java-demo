package com.acoderx.demo.commons.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:guava cache
 *
 * @author xudi
 * @since 2019-01-02
 */
public class CacheLoaderDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)//设置过期时间
                .build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) {
                System.out.println("加载value");
                return key + "'s value";
            }
        });
        System.out.println(cache.get("key"));
        System.out.println(cache.get("key"));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.getIfPresent("key"));
        System.out.println(cache.get("key"));

    }
}
