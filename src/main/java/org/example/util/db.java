package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    public static final String url = "jdbc:postgresql://localhost:5432/jdbc-practice";
    public static final String username = "postgres";
    public static final String password = "2000";

    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to db successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
