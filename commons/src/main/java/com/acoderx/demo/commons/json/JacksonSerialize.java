package com.acoderx.demo.commons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by xudi on 17-6-5.
 */
public class JacksonSerialize {
    public static void main(String args[]) throws ParseException, JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setName("张三");
        user.setNickName("小白");
        user.setPhone("12321233212");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateFormat.parse("2017-05-01"));

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        System.out.println(result);
    }
}
