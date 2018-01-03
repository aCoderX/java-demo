package com.acoderx.demo.jdk.java8.function;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by xudi on 2017/6/26.
 */
public class OptionalDemo {
    public static void main(String[] args){
        String s = null;
        Optional<String> optional = Optional.ofNullable(s);
        //如果非空则执行
        optional.ifPresent(System.out::println);
        //如果为空则返回参数中的字符串
        System.out.println(optional.orElse("is null"));

        //如果满足Predicate则返回，否则返回空
        optional = Optional.of("hahah");
        optional = optional.filter(a -> a.equals("ppp"));
        System.out.println(optional.isPresent());

        //如果function的结果非空 则返回结果 否则返回空
        optional = Optional.of("hahah");
        optional = optional.map((f) -> null);
        System.out.println(optional.isPresent());
    }
}
