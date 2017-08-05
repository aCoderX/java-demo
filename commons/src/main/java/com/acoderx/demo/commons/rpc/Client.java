package com.acoderx.demo.commons.rpc;

/**
 * Created by xudi on 2017/8/5.
 * RPC客户端
 */
public class Client {
    public static void main(String[] args){
        try {
            IHello hello = Register.getRemoteObject(IHello.class, "localhost:9999/hello");
            System.out.println(hello.sayHello("xudi"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
