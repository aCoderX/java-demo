package com.acoderx.demo.commons.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by xudi on 2017/6/24.
 */
public class CglibProxyTest {
    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProxyServiceImpl.class);
        enhancer.setCallback(new CglibProxyHandle());
        CglibProxyServiceImpl proxy = (CglibProxyServiceImpl) enhancer.create();
        proxy.doSomething();
    }
}
