package com.acoderx.demo.jdk.agent;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-08-26
 */
public class AgentHandler {
    public int sum(int a, int b) {
        return a + b;
    }
}
