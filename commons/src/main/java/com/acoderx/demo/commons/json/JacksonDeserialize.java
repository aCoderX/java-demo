package com.acoderx.demo.commons.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by xudi on 17-6-5.
 */
public class JacksonDeserialize {
    public static void main(String args[]) throws IOException {
        String jsonStr = "{\"id\":1,\"name\":\"张三\",\"phoneNumber\":\"12321233212\",\"age\":18,\"nickName\":\"小白\",\"birthday\":1493568000000}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonStr,User.class);
        System.out.println(user.getNickName());
        System.out.println(user.getName());
    }
}
