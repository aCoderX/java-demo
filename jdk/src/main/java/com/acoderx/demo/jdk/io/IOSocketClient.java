package com.acoderx.demo.jdk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by xudi on 17-4-8.
 */
public class IOSocketClient {
    public static void main(String args[]) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",9999));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("helloWorld!!");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("服务器返回:"+reader.readLine());
        socket.close();

        Thread.sleep(5000);
    }
}
