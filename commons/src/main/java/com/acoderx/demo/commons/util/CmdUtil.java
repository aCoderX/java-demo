package com.acoderx.demo.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xudi on 2017/8/20.
 */
public class CmdUtil {
    private static Runtime runtime = Runtime.getRuntime();
    public static void main(String[] args){
        execShell("mvn clean package");
    }

    public static void execShell(String command){
        Process ps;
        BufferedReader br = null;
        try {
            ps = runtime.exec(command);
//            ps = runtime.exec(new String[] { "/bin/sh","-c", command});
            if(ps.waitFor() == 0){
                System.out.println("success");
            }else {
                System.out.println("fail"+ps.waitFor());
            }
            br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            String line;
            while ((line = br.readLine())!=null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
