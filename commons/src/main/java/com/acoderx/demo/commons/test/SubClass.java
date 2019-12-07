package com.acoderx.demo.commons.test;

import java.util.Random;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-01-12
 */
public class SubClass {
    private Random random = new Random();
    public int getRandomC() {
        return random.nextInt(100);
    }

}
