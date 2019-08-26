package com.acoderx.agent;

import java.lang.instrument.Instrumentation;

/**
 * Description: agent demo
 * 可以启动 com.acoderx.demo.jdk.agent.AgentTest
 *
 * @author xudi
 * @since 2019-08-23
 */
public class AgentDemo {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("agentDemo");
        inst.addTransformer(new MyClassFileTransformer());
    }
}
