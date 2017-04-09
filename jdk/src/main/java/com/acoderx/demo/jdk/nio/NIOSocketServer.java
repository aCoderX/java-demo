package com.acoderx.demo.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 17-4-8.
 */
public class NIOSocketServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String args[]) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",9999));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            System.out.println("重新选择");
            int readychannels = selector.select();
            if(readychannels==0){
                continue;
            }
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            while (i.hasNext()){
                SelectionKey key = (SelectionKey) i.next();
                i.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);

                }else if (key.isValid()&&key.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    long startTime = System.currentTimeMillis();
                    int count = socketChannel.read(byteBuffer);
                    if(count>0){
                        byteBuffer.flip();
                        String message = new String(byteBuffer.array());
                        long endTime = System.currentTimeMillis();
                        System.out.println("服务端收到:"+message+"一共花费了："+(endTime-startTime)+"ms");
                        executorService.execute(()->{
                            key.attach(message);
                            //key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                            key.interestOps(SelectionKey.OP_WRITE);
                            selector.wakeup();
                        });
                        //key.attach(message);
                        //key.interestOps(SelectionKey.OP_WRITE);
                    }else{
                        socketChannel.close();
                        System.out.println("客户端断开连接");
                    }


                }else if (key.isValid()&&key.isWritable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    String echoMessage = (String) key.attachment();
                    socketChannel.write(ByteBuffer.wrap(echoMessage.getBytes()));
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}
