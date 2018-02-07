package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudi on 2018/2/7.
 * guava提供的扩展集合-BiMap 双向map
 */
public class BiMapDemo {
    public static void main(String[] args){
        //常规做法
        Map<Integer, String> idToName = new HashMap<>();
        idToName.put(1,"name1");
        idToName.put(2,"name2");
        idToName.put(3,"name3");
        Map<String,Integer> NameToTo = new HashMap<>();
        NameToTo.put("name1",1);
        NameToTo.put("name2",2);
        NameToTo.put("name3",3);
        System.out.println(idToName.get(1));
        System.out.println(NameToTo.get("name1"));

        //BiMap
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1,"name1");
        biMap.put(2,"name2");
        biMap.put(3,"name3");
        System.out.println(biMap.get(1));
        //key value反转
        System.out.println(biMap.inverse().get("name1"));

    }
}
