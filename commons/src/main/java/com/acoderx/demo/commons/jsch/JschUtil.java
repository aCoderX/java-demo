package com.acoderx.demo.commons.jsch;

import com.jcraft.jsch.*;

import java.io.File;

/**
 * Created by xudi on 2017/8/12.
 */
public class JschUtil {
    public static Session getSession(){
        Session session = null;
        try {

            JSch jsch = new JSch();
            File rsa = new File("/Users/xudi/.ssh/id_rsa");

            jsch.addIdentity(rsa.getAbsolutePath(),"");

//            String host = "xudi@localhost";
//            String host = "root@139.199.74.141";
            String host = "ubuntu@54.64.3.35";

            String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);

            session = jsch.getSession(user, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");

            // username and passphrase will be given via UserInfo interface.
            UserInfo ui = new JschUtil.MyUserInfo() {
                public void showMessage(String message) {
                }

                public boolean promptYesNo(String message) {
                    return true;
                }
            };
//            session.setUserInfo(ui);
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }

    public static abstract class MyUserInfo
            implements UserInfo, UIKeyboardInteractive {
        public String getPassword() {
            return null;
        }

        public boolean promptYesNo(String str) {
            return false;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return false;
        }

        public boolean promptPassword(String message) {
            return false;
        }

        public void showMessage(String message) {
        }

        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo) {
            return null;
        }
    }
}
