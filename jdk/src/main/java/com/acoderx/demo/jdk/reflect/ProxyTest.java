package com.acoderx.demo.jdk.reflect;

import com.acoderx.demo.jdk.bean.TestProxy;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * Created by xudi on 2017/6/23.
 * 动态代理demo
 * jdk的动态代理必须依赖于接口 无法直接生成类的代理
 */
public class ProxyTest {
    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyHandle handle = new ProxyHandle();

        ProxyService real = new ProxyServiceImpl();
        ProxyService2 real2 =  new ProxyServiceImpl2();

        Object o = handle.bind(real);
        ProxyService proxy = (ProxyService) o;
        //class com.sun.proxy.$Proxy0
        //proxy 代理类继承自Proxy类,实现了接口ProxyService,所以可以被强制转换
        System.out.println(proxy.getClass());
        System.out.println(ProxyService.class);

        System.out.println(proxy.getClass().getAnnotation(TestProxy.class));
        System.out.println(ProxyServiceImpl.class.getAnnotation(TestProxy.class));

        proxy.doSomething();

        ProxyService2 proxy2 = (ProxyService2) handle.bind(real2);
        proxy2.doOtherSomething();

        System.out.println(AnnotatedElementUtils.hasAnnotation(o.getClass(), TestProxy.class));

    }
}
