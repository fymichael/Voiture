package com.project.Voiture.model.backOffice.statistique;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.project.Voiture.model.backOffice.caracteristique.Voiture;
import com.project.Voiture.model.connection.Connect;

public class Annonce {
    String idAnnonce;
    Voiture voiture;
    String description;
    Date date;
    double prix;
    String idClient;
    int status;

    // methods
    public void insert(Connection con) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "INSERT INTO Annonce VALUES(DEFAULT, ?, ?, default, ?, ?, 1)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, this.getVoiture().getIdVoiture());
            pstmt.setString(2, this.getDescription());
            pstmt.setDouble(3, this.getPrix());
            pstmt.setString(4, this.getIdClient());

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

    // fields / getter / setter / constructor

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public String getIdClient() {
        return idClient;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public double getPrix() {
        return prix;
    }

    public int getStatus() {
        return status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setVoiture(Voiture idVoiture) {
        this.voiture = idVoiture;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Annonce() {
    }

}
