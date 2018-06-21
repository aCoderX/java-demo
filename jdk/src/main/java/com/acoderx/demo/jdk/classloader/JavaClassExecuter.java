package com.acoderx.demo.jdk.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xudi on 2018/6/20.
 */
public class JavaClassExecuter {
    public static String executer(byte[] classBytes){
        HotSwapClassLoader classLoader = new HotSwapClassLoader();
        Class clazz = classLoader.loadByte(classBytes);
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, new String[]{null});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
