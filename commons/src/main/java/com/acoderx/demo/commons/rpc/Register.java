package com.acoderx.demo.commons.rpc;

/**
 * Created by xudi on 2017/8/5.
 * 客户端调用
 * 动态代理工厂
 */
public class Register {
    public static <T> T getRemoteObject(Class<T> clazz,String address) throws IllegalAccessException, InstantiationException {
        RemoteProxyHandle proxyHandle = new RemoteProxyHandle();
        return proxyHandle.bind(clazz,RpcAddress.of(address));
    }
}
