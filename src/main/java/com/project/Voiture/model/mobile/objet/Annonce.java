package com.project.Voiture.model.mobile.objet;

import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Annonce {
    String idAnnonce;
    String idVoiture;
    String description;
    Date date;
    double prix;
    String idClient;
    int status;

    // methods
    // separateur de milliers
    public String formatArgent(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(number).concat(" Ar");
    }

    // formatter un date
    public String formatDate(Date inputDate) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");
        return outputFormat.format(inputDate);
    }

    // fonction pour avoir l'equivalent du status de l'annonce en string
    public String getStringStatus() {
        if (this.getStatus() == 1) {
            return "En attent de validation";
        } else if (this.getStatus() == 10) {
            return "Vendu";
        } else if (this.getStatus() == 0) {
            return "Supprimer";
        } else if (this.getStatus() == 5) {
            return "Disponible";
        }
        return "status annonce inconnue";
    }

    // avoir toutes les annonces du client connecter
    public Vector<Annonce> clientAnnonces(String idClient, Connection con) throws Exception {
        boolean valid = true;
        Statement pstmt = null;
        ResultSet rs = null;

        Vector<Annonce> annonces = new Vector<>();
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select * from annonce where id_client = '" + idClient + "'";
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setIdClient(rs.getString("id_client"));
                a.setIdVoiture(rs.getString("id_voiture"));
                a.setDate(rs.getDate("date"));
                a.setDescription(rs.getString("description"));
                a.setIdAnnonce(rs.getString("id_annonce"));
                a.setPrix(rs.getDouble("prix"));
                a.setStatus(rs.getInt("status"));
                annonces.add(a);
            }
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
        return annonces;
    }

    // ajouter une nouvelle annonce
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

            pstmt.setString(1, this.getIdVoiture());
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

    public String getIdVoiture() {
        return idVoiture;
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

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Annonce() {
    }

}
