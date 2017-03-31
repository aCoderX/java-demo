package com.acoderx.demo.commons.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * Created by xudi on 17-3-31.
 */
public class WriteCSVFile {
    public static void writeCsv(File file){
        try (FileWriter writer = new FileWriter(file);
             CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.DEFAULT)){
            csvPrinter.printRecord(new Object[]{"姓名", "年龄", "性别"});
            csvPrinter.printRecord(new Object[]{"张三","22","男"});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[]){
        URL url = WriteCSVFile.class.getClassLoader().getResource("test.csv");
        writeCsv(new File(url.getFile()));
    }
}
