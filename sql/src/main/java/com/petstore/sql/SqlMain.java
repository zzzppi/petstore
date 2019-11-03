package com.petstore.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlMain {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/petstore?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root123456";

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, user, password);
        PreparedStatement ps = connection.prepareStatement("select * from user");
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }


        }

    }
}
