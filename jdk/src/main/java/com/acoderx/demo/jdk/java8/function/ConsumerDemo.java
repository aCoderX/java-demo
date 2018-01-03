package com.acoderx.demo.jdk.java8.function;

import java.util.function.Consumer;

/**
 * Created by xudi on 17-6-6.
 */
public class ConsumerDemo {
    public static void main(String args[]){
        Consumer<DemoUser> consumer = s -> s.setAge(0);

        DemoUser user = new DemoUser();
        user.setName("TEST");
        user.setAge(18);
        consumer.accept(user);

        System.out.println(user.getAge());
    }
}
