package com.acoderx.demo.jdk.bean;

import java.lang.annotation.*;

/**
 * Created by xudi on 2017/11/18.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited
public @interface TestProxy {
}
