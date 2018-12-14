package com.acoderx.demo.commons.xml.dom;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class Dom4JDemo{

    public Document readXML(String file){
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(file);
        SAXReader saxReader=new SAXReader();
        Document doc=null;
        try{
            doc=saxReader.read(inputStream);
        }catch(DocumentException e){
            e.printStackTrace();
        }
        return doc;
    }

    public static void main(String[] args){
        Dom4JDemo xmlProcessor=new Dom4JDemo();
        Document document=xmlProcessor.readXML("demo.xml");
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            System.out.println(element.getNamespace());
        }

    }
}