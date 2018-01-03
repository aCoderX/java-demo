package com.acoderx.demo.commons.cglib;

import com.acoderx.demo.commons.bean.TestProxy;

/**
 * Created by xudi on 2017/6/24.
 */
@TestProxy
public class CglibProxyServiceImpl {
    public boolean doSomething() {
        System.out.println("do something");
        return true;
    }
}


