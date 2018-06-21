package com.acoderx.demo.jdk.nio;

import java.nio.ByteBuffer;

/**
 * Created by xudi on 2018/6/13.
 */
public class ByteBufferTest {
    public static void main(String[] args){
        int time=10000000;
        long start = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(time * 4);
        for (int i = 0; i < time; i++) {
            byteBuffer.putInt(i);
        }
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            byteBuffer.getInt();
        }
        System.out.println("堆缓冲区读写耗时" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(time * 4);
        for (int i = 0; i < time; i++) {
            byteBuffer2.putInt(i);
        }
        byteBuffer2.flip();
        while (byteBuffer2.hasRemaining()) {
            byteBuffer2.getInt();
        }
        System.out.println("直接缓冲区读写耗时" + (System.currentTimeMillis() - start));

    }
}
