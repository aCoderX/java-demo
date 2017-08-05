package com.acoderx.demo.commons.rpc;

import com.acoderx.demo.commons.util.JsonUtils;
import com.acoderx.demo.commons.util.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudi on 2017/8/5.
 * 服务端调用
 * 监听端口，管理对应关系，做出相应调用
 */
public class RemoteListener {
    private static final Map<String, Class> MAP = new ConcurrentHashMap<>();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    //TODO 安全性 auth
    /**
     * 开始监听
     */
    public static void listen(String host,int port){
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    Socket socket = serverSocket.accept();
                    executorService.execute(() -> {
                        BufferedWriter writer = null;
                        BufferedReader reader = null;
                        try {
                            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            char[] chars = new char[512];
                            String line="";
                            System.out.println("服务端开始接受数据");
                            while((line = reader.readLine())!=null){
//                                while (reader.read(chars, 0, 512) != -1) {
                                System.out.println(line);
                                sb.append(line);
                            }
                            System.out.println("接受完毕");
                            Map<String, Object> param = JsonUtils.toMap(sb.toString());
                            String result = invoke(param);
                            writer.write(result);
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (writer != null) {
                                try {
                                    writer.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (reader != null) {
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
        }).start();
    }
    /**
     * 注册key和对应的class
     * */
    public static void registe(String key,Class clazz){
        MAP.put(key,clazz);
    }

    private static String invoke(Map<String,Object> param){
        String result = "";
        String key = StringUtils.toString(param.get("object"));
        String method = StringUtils.toString(param.get("method"));
        List<String> args = (List<String>) param.get("args");
        Class clazz = MAP.get(key);
        try {
            //TODO 参数类型也需要传递
            Method m = clazz.getMethod(method,String.class);
            result = (String) m.invoke(clazz.newInstance(), args.toArray());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }


}
