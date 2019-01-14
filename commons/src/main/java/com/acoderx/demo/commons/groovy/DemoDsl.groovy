package com.acoderx.demo.commons.groovy

/**
 * groovy dsl demo
 */
class DemoDsl {
    def detailInfo = [:]

    def methodMissing(String name,args) {
        detailInfo[name] = args
    }

    def introduce(closure){
        closure.delegate = this
        closure()
        println "hello,everyone"
        detailInfo.each{
            key,value ->
                println "My $key is $value"
        }
    }

    static void main(String[] args) {
        DemoDsl demoDsl = new DemoDsl()
        demoDsl.introduce({
            name "xd"
            age 18
        })

    }
}
