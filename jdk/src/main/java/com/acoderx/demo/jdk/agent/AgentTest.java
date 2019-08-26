package com.acoderx.demo.jdk.agent;

/**
 * Description:java agent test
 * agent为agent-demo下的 com.acoderx.agent.AgentDemo
 * 启动时加入启动参数 -javaagent:/Users/xudi/code/xudi/java-demo/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar
 *
 * @author xudi
 * @since 2019-08-23
 */
public class AgentTest {
    public static void main(String[] args){
        System.out.println("main");
        AgentHandler agentHandler = new AgentHandler();
        agentHandler.sum(1,2);
    }
}
