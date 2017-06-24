package com.acoderx.demo.commons.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xudi on 2017/6/24.
 */
public class CglibProxyHandle implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before do something");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after do something");
        return null;
    }
}
