package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xudi on 2018/2/7.
 * guava提供的扩展集合-Mutiset 可统计重复值个数
 */
public class MultisetDemo {
    public static void main(String[] args){

        List<String> words = Arrays.asList("a", "d", "c", "e", "d");

        //常规做法
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.merge(word, 1, (oldValue, newVaile) -> oldValue + 1);
        }
        map.forEach((k,v)->System.out.println(k+"--"+v));


        //使用multiset
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.addAll(words);
        for (Multiset.Entry entry : multiset.entrySet()) {
            System.out.println(entry.getElement() + "---" + entry.getCount());
        }
        System.out.println(multiset.count("a"));

    }
}
