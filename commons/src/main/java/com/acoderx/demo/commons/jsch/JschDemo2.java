package com.acoderx.demo.commons.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.io.InputStream;

/**
 * Created by xudi on 2017/8/7.
 * java调用ssh 执行命令
 */
public class JschDemo2 {
    public static void main(String[] args) {
        try {
            Session session = JschUtil.getSession();
            Channel channel = session.openChannel("exec");
            ((ChannelExec)channel).setCommand("ls /root");

//            channel.setInputStream(null);
//            channel.setOutputStream(System.out);


            InputStream in=channel.getInputStream();
            channel.connect();

            ((ChannelExec)channel).setErrStream(System.err);

            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    if(in.available()>0) continue;
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }

            /*while(!channel.isClosed())
                Thread.sleep(500);

            channel.disconnect();*/
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

