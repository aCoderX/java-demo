package com.acoderx.demo.jdk.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by xudi on 17-4-9.
 */
public class AIOSocketServer {
    public static void main(String args[]) throws IOException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9999));
        serverSocketChannel.accept(null,new AcceptHandler());


        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("运行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }

    private static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOSocketServer> {
        @Override
        public void completed(final AsynchronousSocketChannel client, AIOSocketServer attachment) {
            try {
                System.out.println("远程地址：" + client.getRemoteAddress());

                if (client.isOpen()) {
                    System.out.println("client.isOpen：" + client.getRemoteAddress());
                    final ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.clear();
                    client.read(buffer, client, new ReadHandler(buffer));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AIOSocketServer attachment) {

        }
    }


    private static class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {

        private ByteBuffer buffer;

        public ReadHandler(ByteBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
                if (result < 0) {// 客户端关闭了连接
                    System.out.println("断开连接");
                } else if (result == 0) {
                    System.out.println("空数据"); // 处理空数据
                } else {
                    // 读取请求，处理客户端发送的数据
                    buffer.flip();
                    System.out.println("服务器收到："+new String(buffer.array()));

                    //响应操作，服务器响应结果
                    buffer.clear();
                    String res = new String(buffer.array());
                    buffer = ByteBuffer.wrap(res.getBytes());
                    attachment.write(buffer, attachment, new WriteHandler(buffer));//Response：响应。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

        }
    }



    private static class WriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private ByteBuffer buffer;

        public WriteHandler(ByteBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            System.out.println("写完成");
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

        }
    }

}


