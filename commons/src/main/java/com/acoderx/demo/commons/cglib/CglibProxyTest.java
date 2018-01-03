package com.acoderx.demo.commons.cglib;

import com.acoderx.demo.commons.bean.TestProxy;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * Created by xudi on 2017/6/24.
 */
public class CglibProxyTest {
    public static void main(String[] args){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/xudi/tmp/");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProxyServiceImpl.class);
        enhancer.setCallback(new CglibProxyHandle());
        Object o = enhancer.create();
        CglibProxyServiceImpl proxy = (CglibProxyServiceImpl) o;
        //class com.acoderx.demo.commons.cglib.CglibProxyServiceImpl$$EnhancerByCGLIB$$9bb2a23f
        System.out.println(proxy.getClass().getAnnotation(TestProxy.class));
        System.out.println(CglibProxyServiceImpl.class.getAnnotation(TestProxy.class));
        System.out.println(AnnotatedElementUtils.hasAnnotation(CglibProxyServiceImpl.class, TestProxy.class));
        System.out.println(AnnotatedElementUtils.hasAnnotation(proxy.getClass(), TestProxy.class));
        proxy.doSomething();


        System.out.println("-----------");
        System.out.println(AnnotatedElementUtils.hasAnnotation(o.getClass(), TestProxy.class));
    }
}
