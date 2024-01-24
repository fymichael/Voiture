package com.project.Voiture.securite.entite;

import lombok.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.*;

import com.project.Voiture.model.connection.Connect;

@Data @NoArgsConstructor @AllArgsConstructor
public class Profil {
    private String idProfil;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private String mdp;
    private String contact;
    private String username;
    private int idRole;

    public void insert(Connection con) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "INSERT INTO profil VALUES(DEFAULT, ?, ?, ?, ?, ?, 1, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getPrenom());
            pstmt.setDate(3, this.getDateNaissance());
            pstmt.setString(4, this.getEmail());
            pstmt.setString(5, this.getMdp());
            pstmt.setString(6, this.getContact());
            pstmt.setInt(7, this.getIdRole());
            pstmt.setString(8, this.getUsername());

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
}
