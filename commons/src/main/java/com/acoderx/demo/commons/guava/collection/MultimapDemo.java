package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.util.*;

/**
 * Created by xudi on 2018/2/7.
 * guava提供的扩展集合-Mutimap<K,V> 一个key可对应多个value 相当于Map<K,List<V>> 或者Map<K,Set<V>>

 */
public class MultimapDemo {
    public static void main(String[] args){
        List<Temp> list = Arrays.asList(
                new Temp(1,"name1"),
                new Temp(1,"name2"),
                new Temp(2,"name3")
        );

        //常规做法
        Map<Integer, List<Temp>> map = new HashMap<>();
        for (Temp temp : list) {
            map.computeIfAbsent(temp.getId(), (k) -> new ArrayList<>()).add(temp);
        }
        map.forEach((k,v)-> v.forEach((s)->{
            System.out.println(k + "--" + s.getName());
        }));

        //Multimap
        //map可以选择是hashmap或者其他map，collection可以选择是list(arrayList，Linklist...)或其他
        Multimap<Integer,Temp> multimap = MultimapBuilder.hashKeys().arrayListValues().build();
        for (Temp temp : list) {
            multimap.put(temp.getId(), temp);
        }
        multimap.forEach((k,v)-> System.out.println(k + "---" + v.getName()));

    }
    static class Temp{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Temp(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
