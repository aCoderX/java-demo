package com.acoderx.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-08-26
 */
public class MyClassFileTransformer implements ClassFileTransformer {
    /**
     * 每个类被加载时都会调用一次
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println(className);
        return null;
    }
}
