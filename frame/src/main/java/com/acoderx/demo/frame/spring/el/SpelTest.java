package com.acoderx.demo.frame.spring.el;

import com.acoderx.demo.frame.domain.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by xudi on 2018/1/4.
 */
public class SpelTest {
    public static void main(String[] args){
        User user = new User();
        user.setName("啦啦");
        //创建SpEL表达式的解析器
        ExpressionParser parser=new SpelExpressionParser();
        //解析表达式'Hello '+' World!'

        EvaluationContext ctx = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为user，内容为user对象
        ctx.setVariable("user", user);
        String s = parser.parseExpression("#user.getName()+'Hello '+' World!'").getValue(ctx,String.class);
        //输出结果
        System.out.println(s);
    }
}
