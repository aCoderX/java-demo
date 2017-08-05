package com.acoderx.demo.commons.rpc;

/**
 * Created by xudi on 2017/8/5.
 * 服务端的实现类
 */
public class Hello implements IHello {

    @Override
    public String sayHello(String name) {
        System.out.println("服务端sayhello方法被调用");
        return "get it,welcome "+name;
    }
}
