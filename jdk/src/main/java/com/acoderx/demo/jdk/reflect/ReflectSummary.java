package com.acoderx.demo.jdk.reflect;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * Description:反射的基本操作
 *
 * @author: xudi
 * @since: 2018-11-05
 */
public class ReflectSummary {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        //获取class对象
        System.out.println("--------获取class对象----------");
        String s = "";
        System.out.println(s.getClass());
        System.out.println(String.class);
        System.out.println(Class.forName("java.lang.String"));

        //获取类名
        System.out.println("--------获取类名----------");
        System.out.println(String.class.getName());
        System.out.println(String.class.getSimpleName());

        //包信息
        System.out.println("--------包信息----------");
        Package p = String.class.getPackage();
        System.out.println(p);

        //父类
        System.out.println("--------父类----------");
        System.out.println(ArrayList.class.getSuperclass());

        //实现的接口
        System.out.println("--------实现的接口----------");
        Class[] interfaces = ArrayList.class.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

        //构造器
        System.out.println("--------构造器----------");
        Constructor[] constructors = ArrayList.class.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] t = constructor.getParameterTypes();
            for (Class type : t) {
                System.out.print(type.getSimpleName()+"  ");
            }
            System.out.println();
        }

        //方法
        System.out.println("--------方法----------");
        Method[] methods = Class.class.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            System.out.print(method.getReturnType().getSimpleName() + " " + method.getName() + "(");
            for (Parameter parameter : parameters) {
                //1.8可以保存参数名，但是默认不开启，官方认为会导致class变大、容易被反编译，需要打开javac -parameters
                System.out.print(parameter.getType().getSimpleName() + " " + parameter.getName()+",");
            }
            System.out.println(")");
        }


        //修饰符
        System.out.println("--------修饰符----------");
        System.out.println(Modifier.toString(ReflectUser.class.getModifiers()));


        //变量
        System.out.println("--------变量----------");
        //获取所有public（包括父类）的属性
        Field[] f1 = ReflectUser.class.getFields();
        System.out.println(f1.length);
        //获取该类（不包括父类）所有属性
        Field[] f2 = ReflectUser.class.getDeclaredFields();
        for (Field field : f2) {
            System.out.println(Modifier.toString(field.getModifiers())+"  "+field.getType().getSimpleName());
        }


        //注解
        System.out.println("--------注解----------");
        Annotation[] annotations = Resource.class.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof Retention){
                System.out.println(((Retention) annotation).value().name());
            }
            System.out.println(annotation.annotationType().getSimpleName());
        }

        //泛型
        System.out.println("--------获取父类泛型----------");
        System.out.println(((ParameterizedType) ArrayList.class.getGenericSuperclass()).getActualTypeArguments()[0].getTypeName());

        System.out.println("--------接口的泛型----------");
        Type[] types = String.class.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                System.out.println(((ParameterizedType) type).getActualTypeArguments()[0].getTypeName());
            }
        }
        System.out.println("--------变量的泛型----------");
        Field fieldt = ReflectUser.class.getDeclaredField("t");
        TypeVariable tt = (TypeVariable)fieldt.getGenericType();
        System.out.println(tt.getGenericDeclaration() + "--"+tt.getName());
        Field fieldl = ReflectUser.class.getDeclaredField("list");
        ParameterizedType pt = (ParameterizedType) fieldl.getGenericType();
        System.out.println(pt.getActualTypeArguments()[0].getTypeName());
    }
}
