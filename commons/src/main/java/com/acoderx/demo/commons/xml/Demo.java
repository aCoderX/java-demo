package com.acoderx.demo.commons.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Description:java dom解析xml
 *
 * @author xudi
 * @since 2018-12-14
 */
public class Demo {
    public static void main(String[] args){
        try {
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            //需要开启命名空间的支持
            df.setNamespaceAware(true);
            DocumentBuilder db = df.newDocumentBuilder();
            Document document = db.parse(ClassLoader.getSystemResourceAsStream("demo.xml"));
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(node.getNamespaceURI());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
