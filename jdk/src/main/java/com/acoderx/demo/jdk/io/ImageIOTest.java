package com.acoderx.demo.jdk.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by xudi on 2017/7/25.
 * 图片像素过大导致OOM
 * 设置Xmx=256m Xms=256m
 */
public class ImageIOTest {
    public static void main(String[] args) throws IOException {
        //该图片大小为12516*8144
        File file = new File("/Users/xudi/Documents/test.jpg");
        BufferedImage image = ImageIO.read(file);
        System.out.println(image.getHeight() + "---" + image.getWidth());
    }
}
