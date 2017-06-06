package com.acoderx.demo.jdk.reflect;

import java.lang.reflect.Field;

/**
 * Created by xudi on 17-6-5.
 */
public class FieldCopy {
    public static void main(String args[]) throws IllegalAccessException {
        ReflectUser user1 = new ReflectUser();
        user1.setName("李四");
        user1.setPhone("15665657890");
        ReflectUser user2 = new ReflectUser();
        user2.setId(2);
        System.out.println(user1.getClass().getSimpleName());
        Field[] fields = user1.getClass().getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields){
            //允许访问私有属性
            field.setAccessible(true);
            field.set(user2,field.get(user1));
        }
        System.out.println(user2.getName());
    }
}

