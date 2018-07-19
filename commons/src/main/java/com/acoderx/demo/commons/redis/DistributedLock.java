package com.acoderx.demo.commons.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xudi on 2017/6/30.
 */
public class DistributedLock {
    private static final long SLEEPTIME = 1000;
    private static final long INTERVALTIME = 5000;//判断为死锁时间
    private static JedisPool jedisPool = new JedisPool();

    public static String longToString(long num){
        return String.valueOf(num);
    }
    public static String getNowMills(){
        return longToString(System.currentTimeMillis());
    }
    public static boolean checkUnix(String lastUnix){
        long res = System.currentTimeMillis() - Long.valueOf(lastUnix);
        return res>INTERVALTIME;
    }
    // TODO 存在问题
    //1. 由于是客户端自己生成过期时间，所以需要强制要求分布式下每个客户端的时间必须同步。
    //2. 当锁过期的时候，如果多个客户端同时执行jedis.getSet()方法，那么虽然最终只有一个客户端可以加锁，但是这个客户端的锁的过期时间可能被其他客户端覆盖。
    // 3. 锁不具备拥有者标识，即任何客户端都可以解锁。
    public static void tryLock(String key, Runnable runnable){
        Jedis jedis = jedisPool.getResource();
        try {
            while (jedis.setnx(key, getNowMills()) == 0) {
                System.out.println("锁被占用");
                //判断是否可以强制删除锁
                String lastUnix = jedis.get(key);
                if (checkUnix(lastUnix)) {
                    System.out.println("超出最大等待时间，被认定为死锁");
                    //如果超出了最大等待时间，则删除锁
                    //jedis.del(key);
                    //由于删除之后再set不是原则操作，所以不可取，只能使用getset
                    String lastUnixValue = jedis.getSet(key, getNowMills());//此时已经设置成功，但是如果未能成功获取锁，由于写入的都是有效时间戳，并且间隔时间很短，所以不会影响锁
                    if(lastUnixValue.equals(lastUnix)){
                        System.out.println("强制获取锁成功");
                        break;
                    }
                }
                //如果未查询到，睡眠50毫秒
                System.out.println("锁被占用 线程睡眠");
                Thread.sleep(SLEEPTIME);
            }
            System.out.println("正常获取锁成功");
            runnable.run();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("使用结束，释放锁");
            jedis.del(key);
            jedis.close();
        }

    }

    public static void main(String[] args) {
        new Thread(() -> DistributedLock.tryLock("test", () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
        new Thread(() -> DistributedLock.tryLock("test", () -> System.out.println("我是抢占线程"))).start();

    }
}
