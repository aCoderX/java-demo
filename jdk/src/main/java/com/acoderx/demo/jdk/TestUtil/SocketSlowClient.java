package com.acoderx.demo.jdk.TestUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by xudi on 17-4-8.
 */
public class SocketSlowClient {
    private static final int sleep_time=1000*1000*1000;
    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<1;i++){
            executorService.execute(()->{
                Socket socket = new Socket();
                PrintWriter writer = null;
                try {
                    socket.connect(new InetSocketAddress("localhost",9999));
                    writer = new PrintWriter(socket.getOutputStream());
                    LockSupport.parkNanos(sleep_time);
                    writer.print("h");
                    LockSupport.parkNanos(sleep_time);
                    writer.print("e");
                    LockSupport.parkNanos(sleep_time);
                    writer.print("l");
                    LockSupport.parkNanos(sleep_time);
                    writer.print("l");
                    LockSupport.parkNanos(sleep_time);
                    writer.print("o");
                    LockSupport.parkNanos(sleep_time);
                    writer.print("!");
                    LockSupport.parkNanos(sleep_time);
                    writer.println();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    writer.close();
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
