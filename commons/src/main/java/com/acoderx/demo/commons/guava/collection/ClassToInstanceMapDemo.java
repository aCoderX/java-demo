package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudi on 2018/2/7.
 * guava提供的扩展集合-ClassToInstanceMap 通过类型找到对象 相当于Map<Class</? extend B>, B>>
 */
public class ClassToInstanceMapDemo {
    public static void main(String[] args){

        //常规做法
        Map<Class<? extends Number>, Number> map = new HashMap<>();
        map.put(int.class, 1);
        map.put(Integer.class, 2);
        map.put(Double.class, 1.1);
        map.put(Long.class, 1L);
        System.out.println(map.get(Integer.class));
        System.out.println(map.get(int.class));

        //ClassToInstanceMap
        ClassToInstanceMap<Number> classToInstanceMap = MutableClassToInstanceMap.create();//ImmutableClassToInstanceMap、MutableClassToInstanceMap
        classToInstanceMap.putInstance(int.class, 1);
        classToInstanceMap.putInstance(Integer.class, 2);
        System.out.println(classToInstanceMap.getInstance(Integer.class));
    }
}
