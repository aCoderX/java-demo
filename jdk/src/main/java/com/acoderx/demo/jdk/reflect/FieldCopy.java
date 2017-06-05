package com.acoderx.demo.jdk.reflect;

import java.lang.reflect.Field;

/**
 * Created by xudi on 17-6-5.
 */
public class FieldCopy {
    public static void main(String args[]){
        ReflectUser user1 = new ReflectUser();
        ReflectUser user2 = new ReflectUser();
        System.out.println(user1.getClass().getSimpleName());
        Field[] fields = user1.getClass().getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields){
            field.setAccessible(true);

        }
    }
}

