package com.acoderx.demo.jdk.reflect;

/**
 * Created by xudi on 2017/6/23.
 */
public class ProxyServiceImpl implements ProxyService {
    @Override
    public boolean doSomething() {
        System.out.println("I am doing something");
        return true;
    }
}
class ProxyServiceImpl2 implements ProxyService2 {
    @Override
    public boolean doOtherSomething() {
        System.out.println("I am doing other something");
        return true;
    }
}
