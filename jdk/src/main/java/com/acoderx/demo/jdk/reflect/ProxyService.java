package com.acoderx.demo.jdk.reflect;

import com.acoderx.demo.jdk.bean.TestProxy;

/**
 * Created by xudi on 2017/6/23.
 * 动态代理接口
 */
@TestProxy
public interface ProxyService {
    boolean doSomething();
}
interface ProxyService2{
    boolean doOtherSomething();
}
