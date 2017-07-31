package com.acoderx.demo.jdk.number;

import java.math.BigInteger;

/**
 * Created by xudi on 2017/7/31.
 */
public class ByteUtil {
    public static String bytes2BinStr(byte[] bs) {
        BigInteger bigInteger = new BigInteger(bs);
        return bigInteger.toString(2);
    }
    public static String bytes2Hex(byte[] bs) {
        BigInteger bigInteger = new BigInteger(bs);
        return bigInteger.toString(16);
    }
    public static void main(String[] args){
        System.out.println(bytes2BinStr("8".getBytes()));
        System.out.println(bytes2Hex("8".getBytes()));
    }
}
