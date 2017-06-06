package com.acoderx.demo.jdk.java8;

import java.util.function.Function;

/**
 * Created by xudi on 17-6-6.
 */
public class FunctionDemo {
    public static void main(String args[]){
        Function<DemoUser/*输入u*/,String/*返回值*/> function = u->u.getName();
        DemoUser user = new DemoUser();
        user.setAge(18);
        user.setName("张三");

        System.out.println(function.apply(user));
    }
}
