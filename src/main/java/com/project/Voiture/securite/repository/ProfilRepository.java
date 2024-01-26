package com.project.Voiture.securite.repository;

import com.project.Voiture.securite.entite.*;
import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;
import java.time.*;
import java.sql.*;

import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Repository
public class ProfilRepository {
    private PasswordEncoder passwordEncoder;

    public ProfilRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static VProfil findByUsername(String username, Connection connection) {
        VProfil model = new VProfil();
        try {
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.connectDB();
            }

            String sql = "SELECT * FROM v_profil WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
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

    public static VProfil findByEmail(String email, Connection connection) throws Exception {
        VProfil model = new VProfil();
        try {
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.getConnection();
            }

            String sql = "SELECT * FROM v_profil WHERE email = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, email);
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
