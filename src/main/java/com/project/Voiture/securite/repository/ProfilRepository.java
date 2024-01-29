package com.project.Voiture.securite.repository;

import com.project.Voiture.securite.entite.*;
import com.project.Voiture.model.connection.Connect;
import java.time.*;
import java.sql.*;

import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class ProfilRepository {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void insert(Profil profil, Connection con) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "INSERT INTO profil VALUES(DEFAULT, ?, ?, ?, ?, ?, 1, ?, 3, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, profil.getNom());
            pstmt.setString(2, profil.getPrenom());
            pstmt.setDate(3, Date.valueOf(profil.getDateNaissance()));
            pstmt.setString(4, profil.getEmail());
            pstmt.setString(5, passwordEncoder.encode(profil.getMdp()));
            pstmt.setString(6, profil.getContact());
            pstmt.setString(7, profil.getUsername());

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

    public static VProfil findByUsername(String username, Connection connection) {
        VProfil model = new VProfil();
        try {
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.connectDB();
            }

            String sql = "SELECT * FROM v_profil WHERE username = ? OR email = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, username);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.setNom(rs.getString("nom"));
                    model.setPrenom(rs.getString("prenom"));
                    model.setIdProfil(rs.getString("id_profil"));
                    model.setDateNaissance(LocalDate.parse(rs.getDate("date_naissance").toString()));
                    model.setEmail(rs.getString("email"));
                    model.setMdp(rs.getString("mdp"));
                    model.setContact(rs.getString("contact"));
                    model.setIdRole(rs.getInt("id_role"));
                    model.setRole(rs.getString("role"));
                    model.setUsername(rs.getString("username"));
                }
            }

            if (!wasConnected) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public VProfil findByUsername(String username, Connection connection, String mdp) throws Exception {
        VProfil model = new VProfil();
        try {
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.connectDB();
            }

            String sql = "SELECT * FROM v_profil WHERE username = ? and mdp = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, mdp);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.setNom(rs.getString("nom"));
                    model.setPrenom(rs.getString("prenom"));
                    model.setIdProfil(rs.getString("id_profil"));
                    model.setDateNaissance(LocalDate.parse(rs.getDate("date_naissance").toString()));
                    model.setEmail(rs.getString("email"));
                    model.setMdp(rs.getString("mdp"));
                    model.setContact(rs.getString("contact"));
                    model.setIdRole(rs.getInt("id_role"));
                    model.setRole(rs.getString("role"));
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

    public ProfilRepository() {
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}