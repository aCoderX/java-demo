package com.acoderx.demo.jdk.reflect;

/**
 * Created by xudi on 2017/6/23.
 * 动态代理demo
 * jdk的动态代理必须依赖于接口 无法直接生成类的代理
 */
public class ProxyTest {
    public static void main(String[] args){
        ProxyHandle handle = new ProxyHandle();

        ProxyService real = new ProxyServiceImpl();
        ProxyService2 real2 =  new ProxyServiceImpl2();

        ProxyService proxy = (ProxyService) handle.bind(real);
        proxy.doSomething();

        ProxyService2 proxy2 = (ProxyService2) handle.bind(real2);
        proxy2.doOtherSomething();
    }
}
