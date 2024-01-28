package com.project.Voiture.securite.entite;

import lombok.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
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
    private PasswordEncoder passwordEncoder;

    public void insert(Connection con) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "INSERT INTO profil VALUES(DEFAULT, ?, ?, ?, ?, ?, 1, ?, 3, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getPrenom());
            pstmt.setDate(3, Date.valueOf(this.getDateNaissance()));
            pstmt.setString(4, this.getEmail());
            pstmt.setString(5, passwordEncoder.encode(this.getMdp()));
            pstmt.setString(6, this.getContact());
            pstmt.setString(7, this.getUsername());

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

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
