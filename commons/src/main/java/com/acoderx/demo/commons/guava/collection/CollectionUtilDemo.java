package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * Created by xudi on 2018/2/8.
 * guava提供的集合工具
 */
public class CollectionUtilDemo {
    public static void main(String[] args){
        // 集合类的静态方法创建
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        list.forEach(System.out::println);

        // Iterables对于iterable的封装
        Iterable<Integer> iterable = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(2, 3, 4));
        System.out.println(Iterables.frequency(iterable, 2));

        List<Integer> list2 = Lists.reverse(list);
        list2.forEach(System.out::println);
    }
}
