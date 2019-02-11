package com.acoderx.demo.jdk.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xudi on 17-6-8.
 */
public class StreamDemo {
    public static void main(String args[]){
        //流操作
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 8, 8);
        Stream<Integer> intDdemoStream = stream.map(s -> s * s).filter(s -> s != 1).distinct();
        System.out.println(intDdemoStream.findFirst().orElse(1000));

        //map vs flatMap
        Stream<String> stringStream = Stream.of("hello", "word");
        List<String[]> collect = stringStream.map(s -> s.split("")).collect(Collectors.toList());
        collect.forEach(System.out::print);
        System.out.println();
        Stream<String> stringStreamFlatMap = Stream.of("hello", "word");
        //flatmap把各个数组映射成一个流的内容，将map生成的多个数组合并起来
        List<String> collect1 = stringStreamFlatMap.map(s -> s.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        collect1.forEach(System.out::print);
    }
}
