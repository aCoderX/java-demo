package com.acoderx.demo.commons.guava.collection;

import com.acoderx.demo.commons.util.JsonUtils;
import com.google.common.collect.ImmutableSet;

/**
 * Created by xudi on 2018/2/7.
 * 不可修改集合
 */
public class ImmutableCollectionDemo {
    public static final ImmutableSet<String> TEST = ImmutableSet.of("test1","test2");
    public static void main(String[] args){
        ImmutableSet<String> TEST2 = ImmutableSet.<String>builder().addAll(TEST).add("ssss").build();
        ImmutableSet<String> TEST3 = ImmutableSet.copyOf(TEST2);
        System.out.println(JsonUtils.toString(TEST));
        System.out.println(JsonUtils.toString(TEST2));
        System.out.println(JsonUtils.toString(TEST3));
    }
}
