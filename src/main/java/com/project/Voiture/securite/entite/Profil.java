package com.project.Voiture.securite.entite;

import lombok.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
    private PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

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

            System.out.println(pstmt.toString());
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
