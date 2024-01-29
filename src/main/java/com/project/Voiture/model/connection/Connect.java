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

    public static Connection getConnection() throws Exception {     
        // Fonction qui renvoie la connection vers la base : 
            String database = "vaika_occasion";       // Nom de la base
            String user = "postgres";       // User dans postgres
            String mdp = "postgres";       // Mot de passe
            
            Class.forName("org.postgresql.Driver");
            
            // Creation de l'objet de connection
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, user,  mdp);
            
            connection.setAutoCommit(false);
            return connection;
    }
}

