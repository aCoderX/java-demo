package com.acoderx.demo.commons.test.spock

import com.acoderx.demo.commons.test.SubClass
import com.acoderx.demo.commons.test.Sum
import spock.lang.Specification

class SumTest extends Specification {

    def setup() {
        println("setup")
    }

    def cleanup() {
        println("cleanup")
    }

    def setupSpec() {
        println("setupSpec")
    }

    def cleanupSpec() {
        println("cleanupSpec")
    }


    def "测试 #a + #b + #c == #d"() {
        //方法前会初始化
        setup:
        def subClass = Mock(SubClass)
        def sum = new Sum(subClass: subClass)

        //when与then需要搭配使用，在when中执行待测试的函数，在then中判断是否符合预期
        when:
        subClass.randomC >>> c
//        then:1*subClass.randomC//验证在执行sum.sum()的时候 subClass.randomC会被调用一次
        then:
        sum.sum(a, b) == d

        where:
        a | b | c || d
        1 | 1 | 2 || 4
        1 | 2 | 3 || 6
        1 | 3 | 4 || 8
    }
}
