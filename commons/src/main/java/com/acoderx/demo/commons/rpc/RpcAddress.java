package com.acoderx.demo.commons.rpc;

import com.acoderx.demo.commons.util.NumberUtils;
import com.acoderx.demo.commons.util.StringUtils;

/**
 * Created by xudi on 2017/8/5.
 */
public class RpcAddress {
    private String host;
    private int port;
    private String object;

    public static RpcAddress of(String address){
        RpcAddress rpc = new RpcAddress();
        if(StringUtils.isNotEmpty(address)){
            String[] s1 = address.split("/");
            rpc.object = s1.length > 1 ? s1[1] : "rpc";
            String[] s2 = s1[0].split(":");
            rpc.host = s2[0];
            rpc.port = NumberUtils.parseToInt(s2.length > 1 ? s2[1] : "8888");
        }
        return rpc;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
