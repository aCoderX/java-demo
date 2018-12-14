package com.acoderx.demo.commons.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Description:
 *
 * @author: xudi
 * @since: 2018-10-24
 */
public class XmlDemo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
// 1.实例化SAXParserFactory对象
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.创建解析器
        SAXParser parser = factory.newSAXParser();
        // 3.获取需要解析的文档，生成解析器,最后解析文档
        File f = new File("/Users/xudi/Desktop/data.txt");
        SaxHandler saxHandler = new SaxHandler();
        parser.parse(f,saxHandler);
    }
    static class SaxHandler extends DefaultHandler {
        @Override
        public void startElement(String arg0, String arg1, String arg2,
                                 Attributes arg3) throws SAXException {
            System.out.println("开始解析元素 " + arg2);
            if (arg3 != null) {
                for (int i = 0; i < arg3.getLength(); i++) {
                    // getQName()是获取属性名称，
                    System.out.print(arg3.getQName(i) + "=\"" + arg3.getValue(i) + "\"");
                }
            }
            System.out.print(arg2 + ":");
            super.startElement(arg0, arg1, arg2, arg3);
        }

        /* 此方法有三个参数
       arg0是传回来的字符数组，其包含元素内容
       arg1和arg2分别是数组的开始位置和结束位置 */
        @Override
        public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
            String content = new String(arg0, arg1, arg2);
            System.out.println(content);
            super.characters(arg0, arg1, arg2);
        }
    }
}
