package com.acoderx.demo.jdk.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xudi on 2017/10/11.
 */
public class PatternTest {
    public static void main(String[] args){
        //转义正则表达式中的特殊字符为字面量含义
        System.out.println(Pattern.matches(Pattern.quote(".*"),"haha"));
        System.out.println(Pattern.matches(".*","haha"));

        //编译字符串为正则对象，设置模式修正
        Pattern pattern = Pattern.compile("(?<name>[a-z])(\\d)([a-z])",Pattern.CASE_INSENSITIVE);
        //分隔字符串
        String[] ss = pattern.split("987sp7as9s",4);
        for (String s : ss) {
            System.out.println(s);
        }
        System.out.println("--------");
        //获取matcher对象
        Matcher matcher = pattern.matcher("987sp7as9s");
        if (matcher.find()) {
            //group可以理解为子表达式，是对于pattern中的()而言的
            System.out.println(matcher.group());//默认为0，表示所有组合起来匹配到的，即p7a
            System.out.println(matcher.group("name"));//命名组
            System.out.println(matcher.group(1));//表示匹配到的字符串对应的pattern中第一个()中的内容，即p
            System.out.println(matcher.group(2));
            System.out.println(matcher.groupCount());//表示一共有多少个()
        }

    }
}
