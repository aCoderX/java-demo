package com.acoderx.demo.jvm;

/**
 * Created by xudi on 2018/1/4.
 * JVM参数 -XX:+OmitStackTraceInFastThrow(默认) 会在频繁抛出一个异常后只抛出没有堆栈的异常，需设置为-XX:-OmitStackTraceInFastThrow
 */
public class JVMTest {
    private static final int count = 8000;
    /**
     * @param args
     */
    public static void main(String[] args)throws Exception {
        int index = count;
        while(index -- > 0){
            try {
                work();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void work(){
        String value = null;
        value.length();
    }
}
