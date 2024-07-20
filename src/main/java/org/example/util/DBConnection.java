package org.example.util;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/db";
        String user = "postgres";
        String passwd = "root";
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, passwd);
    }

}
