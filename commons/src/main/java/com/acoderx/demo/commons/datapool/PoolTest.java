package com.acoderx.demo.commons.datapool;

import java.sql.*;

/**
 * Created by xudi on 2018/4/27.
 */
public class PoolTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:wrap-jdbc:jdbc:mysql://localhost:3306/demo", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id=1");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
