package com.acoderx.demo.jdk.classloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xudi on 2018/6/20.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("/tmp/TestClass.class");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        JavaClassExecuter.executer(bytes);
    }
}
