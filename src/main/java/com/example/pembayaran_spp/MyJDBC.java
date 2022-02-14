package com.example.pembayaran_spp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC {

    public static Connection gConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pembayaran_spp", "root", "");
        
    }

    // System.out.println("Database Success");


}
