package com.acoderx.demo.jdk.reflect;

import com.acoderx.demo.jdk.bean.TestProxy;

/**
 * Created by xudi on 2017/6/23.
 */
@TestProxy
public class ProxyServiceImpl implements ProxyService {
    @Override
    public boolean doSomething() {
        System.out.println("I am doing something");
        return true;
    }
    public void test(){
        System.out.println("test");
    }
}
class ProxyServiceImpl2 implements ProxyService2 {
    @Override
    public boolean doOtherSomething() {
        System.out.println("I am doing other something");
        return true;
    }
}
