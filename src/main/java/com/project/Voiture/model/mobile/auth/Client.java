package com.project.Voiture.model.mobile.auth;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.project.Voiture.model.connection.Connect;

public class Client {
    String idClient;
    String nom;
    String prenom;
    Date dateNaissance;
    String email;
    String mdp;

    // methods
    public void insert(Connection con) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "INSERT INTO Client VALUES(DEFAULT, ?, ?, ?, ?, ?, 1)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getPrenom());
            pstmt.setDate(3, this.getDateNaissance());
            pstmt.setString(4, this.getEmail());
            pstmt.setString(5, this.getMdp());

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

    // fiels - constructor - getter and setter
    public Client() {
    }   
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getNom() {
        return nom;
    }
    public String getMdp() {
        return mdp;
    }
    public String getIdClient() {
        return idClient;
    }
    public String getEmail() {
        return email;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
}
