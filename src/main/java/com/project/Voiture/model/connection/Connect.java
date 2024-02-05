package com.project.Voiture.model.connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Connection con;

    public static Connection connectDB() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:postgresql://viaduct.proxy.rlwy.net:32444/railway", "postgres", "41d5e*5G1*B42D6CgDe4fbc2CA3Eba*3");
        return con;
    }
}

