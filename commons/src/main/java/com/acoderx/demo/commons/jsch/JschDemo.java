package com.acoderx.demo.commons.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

/**
 * Created by xudi on 2017/8/7.
 * java调用ssh 获取shell
 */
public class JschDemo {
    public static void main(String[] args) {
        try {
            Session session = JschUtil.getSession();
            Channel channel = session.openChannel("shell");

            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);

            channel.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

