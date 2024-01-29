package com.project.Voiture.securite.entite;

import lombok.*;
import java.sql.*;
import java.time.LocalDate;

import com.project.Voiture.model.connection.Connect;

@Data @NoArgsConstructor @AllArgsConstructor
public class Login {
    private String username;
    private String mdp;

///Fonctions
    //Verifier l'authentification par username
    public static VProfil authentificatedByUsernameOrEmail(String username, Connection connection) throws Exception {
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
               
                if(model.getIdProfil() == null) {
                    return null;
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

        //Verifier mot de passe
        public static boolean authentificatedByMdp(String mdpUser, String mdpTrue) throws Exception {
            boolean isAuthentificated = false;
                if(mdpUser.equals(mdpTrue)) {
                    isAuthentificated = true;
                }

            return isAuthentificated;
        }
    }
