package com.acoderx.demo.jdk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 17-4-8.
 */
public class IOSocketServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String args[]){
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("新连接");
                executorService.execute(() -> {
                    PrintWriter printWriter = null;
                    BufferedReader reader = null;
                    long startTime = System.currentTimeMillis();
                    long endTime;
                    try {
                        printWriter = new PrintWriter(socket.getOutputStream());
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String line;
                        System.out.println("准备读数据");
                        while((line = reader.readLine())!=null){
                            System.out.println(line);
                            printWriter.println(line);
                            printWriter.flush();
                        }
                        System.out.println("读完数据");
                        endTime = System.currentTimeMillis();
                        System.out.println("花费"+(endTime-startTime)+"ms");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(printWriter != null){
                            printWriter.close();
                        }
                        if(reader != null){
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
