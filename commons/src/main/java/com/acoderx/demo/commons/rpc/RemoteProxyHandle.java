package com.acoderx.demo.commons.rpc;

import com.acoderx.demo.commons.util.JsonUtils;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudi on 2017/8/5.
 * 客户端调用
 * 动态代理
 */
public class RemoteProxyHandle implements InvocationHandler {
    private RpcAddress targetAddress;

    public <T> T bind(Class<T> clazz ,RpcAddress rpcAddress){
        this.targetAddress = rpcAddress;
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据address连接远程服务器
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(targetAddress.getHost(), targetAddress.getPort()));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        //传递调用的对象，方法，参数
        Map<String, Object> map = new HashMap<>();

        map.put("object",targetAddress.getObject());
        map.put("method",method.getName());
        map.put("args", Arrays.asList(args));

        System.out.println("客户端发送：" + JsonUtils.toString(map));
//        writer.write(JsonUtils.toString(map));
        writer.println(JsonUtils.toString(map));
        writer.flush();

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
        char[] b = new char[512];
        StringBuilder builder = new StringBuilder();
        System.out.println("等待返回数据");
        while(reader.read(b,0,512)!=-1){
            System.out.println(b);
            builder.append(b);
        }*/
        socket.close();

        //得到结果，返回结果
//        return builder.toString();
        return "";
    }
}
