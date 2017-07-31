package com.acoderx.demo.jdk.io;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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

        //不需要加载整个图片获取宽高
        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("JPG");
        if (iterator.hasNext()) {
            ImageReader reader = iterator.next();
            ImageInputStream stream = ImageIO.createImageInputStream(file);
            reader.setInput(stream,true,true);
            System.out.println(reader.getHeight(0));
            System.out.println(reader.getWidth(0));
        }
    }
}
