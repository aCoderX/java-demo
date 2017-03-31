package com.acoderx.demo.commons.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by xudi on 17-3-31.
 */
public class ReadCSVFile {
    public static void readCsv(File file) {
        try (FileReader reader = new FileReader(file);
            //用文件第一行作为头
             CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT.withFirstRecordAsHeader())){
            List<CSVRecord> records = csvParser.getRecords();
            for(int i = 0; i < records.size(); i++){
                System.out.println(records.get(i).toString());
                //通过标题名获取
                System.out.println(records.get(i).get("姓名"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[]){
        URL url = ReadCSVFile.class.getClassLoader().getResource("test.csv");
        readCsv(new File(url.getFile()));
    }
}
