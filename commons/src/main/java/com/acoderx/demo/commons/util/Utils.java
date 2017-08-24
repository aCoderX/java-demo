package com.acoderx.demo.commons.util;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xudi on 2017/8/23.
 */
public class Utils {

    public static void main(String[] args) throws IOException, XmlPullParserException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Class.class.getClass().getResource("/").getPath());
        File file = new File("pom.xml");
        System.out.println(file.getAbsolutePath());

        readPom();
    }

    public static String readParamFromFile(String paramName){
        String result = "";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("pomprofile.properties"));
            result = prop.getProperty(paramName);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void readPom() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        System.out.println(model.getId());
        System.out.println(model.getGroupId());
        System.out.println(model.getArtifactId());
        System.out.println(model.getVersion());
        System.out.println(model.getPackaging());
        System.out.println(model.getModules());
    }
}
