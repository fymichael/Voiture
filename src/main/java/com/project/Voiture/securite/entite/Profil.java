package com.project.Voiture.securite.entite;

import lombok.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;

import com.project.Voiture.model.connection.Connect;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Profil login(Connection con) throws Exception {
        boolean valid = true;
        Statement statement = null;
        Profil profilConnecte = null;
        ResultSet rs = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select * from from profil where username = '"+ this.getUsername() +"' and mdp = '"+ this.getMdp() +"' and status = 1";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next()){
                profilConnecte = new Profil();
                profilConnecte.setContact(rs.getString("contact"));
                profilConnecte.setDateNaissance(rs.getDate("date_naissance"));
                profilConnecte.setEmail(rs.getString("email"));
                profilConnecte.setIdRole(3);
                profilConnecte.setIdProfil(rs.getString("id_profil"));
                profilConnecte.setMdp(rs.getString("mdp"));
                profilConnecte.setNom(rs.getString("nom"));
                profilConnecte.setPrenom(rs.getString("prenom"));
                profilConnecte.setUsername(rs.getString("username"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (!valid) {
                con.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return profilConnecte;
    }

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
            pstmt.setDate(3, this.getDateNaissance());
            pstmt.setString(4, this.getEmail());
            pstmt.setString(5, this.getMdp());
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
}
