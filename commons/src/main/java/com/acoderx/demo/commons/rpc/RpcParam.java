package com.acoderx.demo.commons.rpc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xudi on 2017/8/5.
 * 用于客户端、服务端之间的参数传递
 */
public class RpcParam {
    private String object;//调用对象注册的key
    private String method;//调用的方法
    private Map<String, Object> params = new LinkedHashMap<>();

    public void addParam(String s, Object o) {
        params.put(s, o);
    }
}
