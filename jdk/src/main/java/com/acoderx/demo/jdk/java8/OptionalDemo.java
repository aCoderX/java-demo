package com.acoderx.demo.jdk.java8;

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
    }
}
