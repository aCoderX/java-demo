package com.acoderx.demo.commons.tuples;

import org.javatuples.Triplet;

/**
 * Description:元组
 *
 * @author xudi
 * @since 2019-03-06
 */
public class TuplesDemo {
    public static void main(String[] args){
        Triplet test = test();
        for (Object o : test) {
            System.out.println(o);
        }
    }

    private static Triplet test() {
        return Triplet.with(1, 2, 3);
    }
}
