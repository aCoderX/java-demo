package com.acoderx.demo.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by xudi on 2017/8/5.
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String toString(Object o){
        String result = "";
        if(o!=null){
            try {
                result = MAPPER.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Map<String, Object> toMap(String jsonString) {
        if(StringUtils.isNotEmpty(jsonString)){
            try {
                return (Map) MAPPER.readValue(jsonString, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyMap();
    }
}
