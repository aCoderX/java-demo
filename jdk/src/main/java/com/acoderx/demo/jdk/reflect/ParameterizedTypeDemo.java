package com.acoderx.demo.jdk.reflect;


import java.lang.reflect.*;
import java.util.List;

/**
 * Description:获取泛型类型
 *
 * @author: xudi
 * @since: 2018-11-01
 */
public class ParameterizedTypeDemo {
    public static void main(String[] args) {
        getActualTypeArgument("[{\"name\":\"s\"}]\n",new TypeReference<List<ReflectUser>>(){});
    }

    /**
     * 有时候需要查询到list中的泛型类型，比如需要将字符串s反序列化成List
     * @param s
     * @param typeReference
     */
    private static void getActualTypeArgument(String s, TypeReference<List<ReflectUser>> typeReference) {
        System.out.println("--------------------");
        System.out.println(typeReference.getType());
        System.out.println(((ParameterizedType) typeReference.getType()).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedType) typeReference.getType()).getRawType());
    }


    abstract static class TypeReference<T> {
        public Type type;

        TypeReference() {
            ParameterizedType pt = ((ParameterizedType) this.getClass().getGenericSuperclass());
            //输出<>中内容
            System.out.println(pt.getActualTypeArguments()[0]);
            //如果是内部内，输出外部类信息，否则输出null
            System.out.println(pt.getOwnerType());
            //输出<>外内容
            System.out.println(pt.getRawType());
            for( int i = 0; i < pt.getActualTypeArguments().length; i++ ){
                printType(pt.getActualTypeArguments()[i].toString(), pt.getActualTypeArguments()[i]);
            }

            this.type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }

        public Type getType() {
            return type;
        }
    }

    public static void printType(String name, Type type){
        if( type instanceof Class ){
            System.out.println("the type of " + name + " is : Class");
        }else if( type instanceof ParameterizedType ){
            //泛型，如List<T>
            System.out.println("the type of " + name + " is : ParameterizedType");
        }else if( type instanceof GenericArrayType){
            //泛型数组 T[]
            System.out.println("the type of " + name + " is : GenericArrayType");
        }else if( type instanceof TypeVariable){
            //泛型中的变量，T、E、K、V
            System.out.println("the type of " + name + " is : TypeVariable");
        }

    }
}
