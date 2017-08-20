package com.acoderx.demo.commons.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.*;

/**
 * Created by xudi on 2017/8/20.
 */
public class JschScp {
    //远程文件路径
    public static final String RFILE = "/tmp/target.txt";
    //本地文件路径
    public static final String LFILE = "/tmp/1.txt";

    public static void main(String[] args) throws JSchException, IOException {
        Session session = JschUtil.getSession();
        Channel channel = session.openChannel("exec");
        String command = "scp -t " + RFILE;
        ((ChannelExec) channel).setCommand(command);
        channel.connect();
        OutputStream out = channel.getOutputStream();
        InputStream in = channel.getInputStream();

        if(checkAck(in)!=0){
            System.exit(0);
        }


        File _lfile = new File(LFILE);


        long filesize=_lfile.length();
        command="C0644 "+filesize+" ";
        if(LFILE.lastIndexOf('/')>0){
            command+=LFILE.substring(LFILE.lastIndexOf('/')+1);
        }
        else{
            command+=LFILE;
        }
        command+="\n";
        out.write(command.getBytes()); out.flush();

        if(checkAck(in)!=0){
            System.exit(0);
        }

        FileInputStream fis;
        fis = new FileInputStream(_lfile);
        byte[] buf = new byte[1024];
        while (true) {
            int len = fis.read(buf, 0, buf.length);
            if (len <= 0) break;
            out.write(buf, 0, len); //out.flush();
        }
        fis.close();
        // send '\0'
        buf[0] = 0;
        out.write(buf, 0, 1);
        out.flush();
        if(checkAck(in)!=0){
            System.exit(0);
        }
        out.close();
        channel.disconnect();
        session.disconnect();
    }

    static int checkAck(InputStream in) throws IOException{
        int b=in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if(b==0) return b;
        if(b==-1) return b;

        if(b==1 || b==2){
            StringBuffer sb=new StringBuffer();
            int c;
            do {
                c=in.read();
                sb.append((char)c);
            } while(c!='\n');
            if(b==1){ // error
                System.out.print(sb.toString());
            }
            if(b==2){ // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }
}
