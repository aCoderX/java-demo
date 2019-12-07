package com.acoderx.demo.commons.test;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-01-12
 */
public class Sum {
    private SubClass subClass;

    public int sum(int a, int b) {
        int c = subClass.getRandomC();
        return a + b + c;
    }

    public void setSubClass(SubClass subClass) {
        this.subClass = subClass;
    }
}
