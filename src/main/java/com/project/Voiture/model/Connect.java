package com.project.Voiture.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Connection con;

    public static Connection connectDB() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voiture", "postgres", "mdpprom15");
        return con;
    }
}

