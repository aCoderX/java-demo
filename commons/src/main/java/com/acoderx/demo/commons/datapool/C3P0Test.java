package com.acoderx.demo.commons.datapool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xudi on 2018/4/27.
 */
public class C3P0Test {
    public static void main(String[] args) throws SQLException {
        DataSource ds = new ComboPooledDataSource();
        Connection connection = ds.getConnection();
        connection.createStatement();
    }
}
