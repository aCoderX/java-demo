package com.acoderx.demo.jdk.reflect;

import java.util.Date;
import java.util.List;

/**
 * Created by xudi on 17-6-5.
 */
public class ReflectUser<T> {
    private int id;
    private String name;
    private String phone;
    private int age;
    private Date birthday;
    private T t;
    private List<String> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
