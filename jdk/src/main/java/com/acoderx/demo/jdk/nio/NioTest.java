package com.acoderx.demo.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xudi on 17-4-12.
 */
public class NioTest {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 打开 Selector
        Selector selector = Selector.open();

        // 服务端 Socket 监听8080端口, 并配置为非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        // 将 channel 注册到 selector 中.
        // 通常我们都是先注册一个 OP_ACCEPT 事件, 然后在 OP_ACCEPT 到来时, 再将这个 Channel 的 OP_READ
        // 注册到 Selector 中.
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        int count=selector.select();
                        System.out.println("1ready channel count "+count);
                        Set<SelectionKey> keys = selector.selectedKeys();
                        System.out.println(keys.size());
                        Iterator<SelectionKey> i = keys.iterator();
                        while (i.hasNext()){
                            SelectionKey key = i.next();
                            if(key.isAcceptable()){
                                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                SocketChannel client = server.accept();
                                /*client.configureBlocking(false);*/
                                //client.finishConnect();
                                //client.register(selector,SelectionKey.OP_READ);
                            }
                        }
                        //keys.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.sleep(50000);
        System.out.println("process end");
    }
}