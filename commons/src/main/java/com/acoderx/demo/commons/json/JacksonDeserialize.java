package com.acoderx.demo.commons.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;

/**
 * Created by xudi on 17-6-5.
 * jackson有三种处理json的方式
 * 1：数据绑定，与java实体类映射（最方便）
 * 2：解析树，使用ObjectNode、ArrayNode进行解析（最灵活）
 * 3：流操作，使用JsonGenerator、JsonParser，是数据绑定和树模型的底层处理模型
 * jackson github文档：https://github.com/FasterXML/jackson-databind/
 */
public class JacksonDeserialize {
    public static void main(String args[]) throws IOException {
        System.out.println("---------数据绑定---------------");
        //数据绑定
        String jsonStr = "{\"id\":1,\"name\":\"张三\",\"phoneNumber\":\"12321233212\",\"age\":18,\"nickName\":\"小白\",\"birthday\":1493568000000}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        User user = mapper.readValue(jsonStr,User.class);
        System.out.println(user.getNickName());
        System.out.println(user.getName());

        System.out.println("---------解析树---------------");
        //解析树
        JsonNode jsonNode = mapper.readTree("{\"id\":1,\"name\":\"张三\",\"phoneNumber\":\"12321233212\",\"age\":18,\"nickName\":\"小白\",\"birthday\":1493568000000}");
        System.out.println(jsonNode.get("id"));
        System.out.println(mapper.writeValueAsString(jsonNode));

        System.out.println("--------流操作----------------");
        //流操作
        JsonFactory jsonFactory = mapper.getFactory();
        JsonParser parser = jsonFactory.createParser("{\"id\":1,\"name\":\"张三\",\"phoneNumber\":\"12321233212\",\"age\":18,\"nickName\":\"小白\",\"birthday\":1493568000000}");
        JsonToken t = parser.nextToken();//获取到{
        t = parser.nextToken();//获取到"id"
        if ((t == JsonToken.FIELD_NAME) && "id".equals(parser.getCurrentName())) {
            t = parser.nextToken();//获取到1
            if (t == JsonToken.VALUE_NUMBER_INT) {
                System.out.println("JsonFactory解析到id："+parser.getText());
            }
        }

        System.out.println("--------转换功能----------------");
        //jackson转换功能，可以将一个pojo转成另一个pojo
        int[] ints = {1, 2, 3, 4};
        List list = mapper.convertValue(ints, List.class);
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
