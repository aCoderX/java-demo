package com.acoderx.demo.commons.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xudi on 2018/2/7.
 * guava提供的扩展集合-table 二维数据(keyX,keyY,value) 相当于Map<keyX, Map<keyY, value>>
 */
public class TableDemo {
    public static void main(String[] args){

        Table<Integer, Integer, String> table = HashBasedTable.create();

        table.put(1,1,"name11");
        table.put(1,2,"name12");
        table.put(4,2,"name42");

        //遍历  类似于map的entry
        for (Table.Cell cell : table.cellSet()) {
            System.out.println(cell.getRowKey() + "--" + cell.getColumnKey() + "--" + cell.getValue());
        }

        Map<Integer,Map<Integer,String>> map = table.columnMap();
        System.out.println(map.get(1).get(1));

    }
}
