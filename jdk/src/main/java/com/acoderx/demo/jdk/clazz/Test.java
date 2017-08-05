package com.acoderx.demo.jdk.clazz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xudi on 2017/7/31.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        /*Demo1<Integer> demo1 = new Demo1<>();
        System.out.println(demo1.get(1));
        Demo1<Double> demo2 = new Demo1<>();
        System.out.println(demo2.get(1.1));*/
//        test(demo1);
        System.out.println(getObject(String.class).getClass().getName());
    }

    private static void test(Demo1<? extends Number> demo1) {
        demo1.print();
    }
    private static <T> T getObject(Class<T> c) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        System.out.println(c.getName());
        Method m = c.getMethod("length");
        Constructor constructor = c.getConstructor(String.class);
        T ins = (T) constructor.newInstance("ssssss");
        System.out.println(m.invoke(ins));
        return ins;
    }
}

class Demo1<T extends Number> {
    T get(T value){
        return value;
    }
    void print() {

    }
}