package com.acoderx.demo.commons.hessian;

import com.acoderx.demo.commons.rpc.IHello;
import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created by xudi on 2017/8/6.
 */
public class HessianClient {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String url = "http://localhost:8080/jms1/helloworld";
        HessianProxyFactory factory = new HessianProxyFactory();
        IHello hello = (IHello) factory.create(url);
        hello.sayHello("xudi");
    }
}
