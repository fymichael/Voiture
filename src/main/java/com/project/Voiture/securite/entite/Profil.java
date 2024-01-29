package com.project.Voiture.securite.entite;

import lombok.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.Voiture.model.connection.Connect;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profil {
    private String idProfil;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String mdp;
    private String contact;
    private String username;
    private int idRole;

     //Trouver par le mmot de passe
     public static Profil findById(String idProfil, Connection connection) throws Exception {
        Profil model = new Profil();
        try {
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.connectDB();
            }

            String sql = "SELECT * FROM profil WHERE id_profil = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, idProfil);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.setIdProfil(rs.getString("id_profil"));
                    model.setNom(rs.getString("nom"));
                    model.setPrenom(rs.getString("prenom"));
                    model.setDateNaissance(LocalDate.parse(rs.getDate("date_naissance").toString()));
                    model.setEmail(rs.getString("email"));
                    model.setMdp(rs.getString("mdp"));
                    model.setContact(rs.getString("contact"));
                    model.setIdRole(rs.getInt("id_role"));
                    model.setUsername(rs.getString("username"));
                }
            }

            if (!wasConnected) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return model;
    }
}
