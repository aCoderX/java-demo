package com.acoderx.demo.jdk.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xudi on 2017/6/23.
 */
public class ProxyHandle implements InvocationHandler {
    private Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before do something");
        Object result = method.invoke(target, args);
        System.out.println("after do something");
        return result;
    }
}
