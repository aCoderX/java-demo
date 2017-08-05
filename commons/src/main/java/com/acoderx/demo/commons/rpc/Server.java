package com.acoderx.demo.commons.rpc;

/**
 * Created by xudi on 2017/8/5.
 * 服务端调用
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        RemoteListener.listen("localhost",9999);
        RemoteListener.registe("hello",Hello.class);
        Thread.sleep(1000000);
    }
}
