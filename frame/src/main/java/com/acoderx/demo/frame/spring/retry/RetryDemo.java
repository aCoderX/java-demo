package com.acoderx.demo.frame.spring.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-01-10
 */
public class RetryDemo {
    public static void main(String[] args) throws Exception {
        RetryTemplate template = new RetryTemplate();

        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(3);

        template.setRetryPolicy(policy);

        String result = template.execute(new RetryCallback<String,Exception>() {

            public String doWithRetry(RetryContext context) {
                System.out.println("lllala");
                throw new RuntimeException();
            }
        });
    }
}
