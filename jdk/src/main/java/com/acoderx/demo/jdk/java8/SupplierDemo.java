package com.acoderx.demo.jdk.java8;

import java.util.function.Supplier;

/**
 * Created by xudi on 17-6-6.
 */
public class SupplierDemo {
    public static void main(String args[]){
        Supplier<String> supplier = ()-> {String s="abc"; return s.toUpperCase();};
        System.out.println(supplier.get());
    }
}
