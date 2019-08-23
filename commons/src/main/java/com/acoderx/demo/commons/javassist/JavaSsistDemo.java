package com.acoderx.demo.commons.javassist;

import javassist.*;

/**
 * Description:字节码增强包，可以用来实现AOP
 *
 * @author xudi
 * @since 2019-08-23
 */
public class JavaSsistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        //classPool 相当于CtClass对象的容器，每一个CtClass都必须从ClassPool中获取
        ClassPool pool = ClassPool.getDefault();
        //javassit为每个需要编辑的class都创建的ctClass对象，通过对CtClass对象的操作来实现对class的编辑
        CtClass ctClass = pool.getCtClass("com.acoderx.demo.commons.javassist.CtHandler");
        //方法
        CtMethod sumMethod = ctClass.getDeclaredMethod("sum");
        /**
         * 占位符
         * $0, $1, $2, ...	方法的参数
         * $args	方法参数数组.它的类型为 Object[]
         * $$	所有实参。例如, m($$) 等价于 m($1,$2,...)
         * $cflow(...)	cflow 变量
         * $r	返回结果的类型，用于强制类型转换
         * $w	包装器类型，用于强制类型转换
         * $_	返回值
         * $sig	类型为 java.lang.Class 的参数类型数组
         * $type	一个 java.lang.Class 对象，表示返回值类型
         * $class	一个 java.lang.Class 对象，表示当前正在修改的类
         */
        sumMethod.insertBefore("System.out.println(\"before sum args:\"+($1));");
        sumMethod.insertAfter("System.out.println(\"after sum,return \"+$_);");
        //通过类加载器加载该CtClass
        ctClass.toClass();

        CtHandler ctHandler = new CtHandler();
        ctHandler.sum(1, 2);
        System.out.println(ctHandler.getClass().getClassLoader().getName());

    }
}
