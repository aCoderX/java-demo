package com.acoderx.demo.commons.datapool;

import java.sql.*;

/**
 * Created by xudi on 2018/4/26.
 */
public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //由于jdbc使用了SPI 已经不需要手动注册
//        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id=1");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
