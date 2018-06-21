package com.acoderx.demo.jdk.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by xudi on 2018/6/13.
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("/Users/xudi/Desktop/0.jpg"), StandardOpenOption.READ);
        FileChannel channel2 = FileChannel.open(Paths.get("/Users/xudi/Desktop/2.jpg"),StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            channel2.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
