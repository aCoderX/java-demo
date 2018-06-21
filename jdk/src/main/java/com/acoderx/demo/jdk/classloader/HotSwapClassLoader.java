package com.acoderx.demo.jdk.classloader;

/**
 * Created by xudi on 2018/6/20.
 */
public class HotSwapClassLoader extends ClassLoader {
    public Class loadByte(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
