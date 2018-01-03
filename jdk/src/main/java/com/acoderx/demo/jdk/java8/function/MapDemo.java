package com.acoderx.demo.jdk.java8.function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudi on 17-6-8.
 */
public class MapDemo {
    public static void main(String args[]){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"test1");
        map.put(2,"test2");
        map.put(3,"test3");

        //由于map不支持steam操作，所以java8增加了许多新方法
        map.forEach((k,v)->System.out.println(k));

        //如果1这个key的值为null，则根据后面的lambda计算出value并赋值
        map.computeIfAbsent(4,k-> k+"--");//absent 缺席
        map.forEach((k,v)->System.out.println(k+"---"+v));

        //如果3这个key的值存在，则根据后面的lambda计算出value并赋值
        map.computeIfPresent(3,(k,v)->"NO");
        map.forEach((k,v)->System.out.println(k+"---"+v));

        //如果不存在就赋值
        map.putIfAbsent(5,"test5");
        map.forEach((k,v)->System.out.println(k+"---"+v));

        //计算值并赋值
        map.compute(6,(k,v)->"test"+k);
        map.forEach((k,v)->System.out.println(k+"---"+v));
        map.compute(6,(k,v)->"test"+k+"test");
        map.forEach((k,v)->System.out.println(k+"---"+v));

        //将旧的值和新的值合并
        map.merge(6,"new6",(oldV,newV)->oldV+newV);
        map.forEach((k,v)->System.out.println(k+"---"+v));

        System.out.println(map.getOrDefault(10,"no key 10"));
    }
}
