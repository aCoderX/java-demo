package com.acoderx.demo.frame.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudi on 2017/6/17.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-transaction.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        try {
//        userService.testPropagation_REQUIRED();
//        userService.testPropagation_SUPPORTS();
//        userService.testPropagation_MANDATORY();
//        userService.testPropagation_REQUIRENEW();
//        userService.testPropagation_NOTSUPPORTED();
//        userService.testPropagation_NEVER();
        userService.testPropagation_NESTED();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userService.showResult();
        }
    }
}
