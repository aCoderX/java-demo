package com.acoderx.demo.jdk.java8;

import java.util.function.Predicate;

/**
 * Created by xudi on 17-6-6.
 */
public class PredicateDemo {
    public static void main(String args[]){
        //判断条件
        Predicate<String> predicate = s -> s.equals("test");

        System.out.println(predicate.test("hello"));
        System.out.println(predicate.test("test"));
        //取反
        System.out.println(predicate.negate().test("test"));
        //增加条件
        System.out.println(predicate.and(s -> s.startsWith("tes")).test("test"));
        System.out.println(predicate.or(s -> s.startsWith("hel")).test("test"));

    }
}
