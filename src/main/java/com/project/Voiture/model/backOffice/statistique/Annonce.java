package com.project.Voiture.model.backOffice.statistique;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

import com.project.Voiture.model.backOffice.caracteristique.Voiture;
import com.project.Voiture.model.connection.Connect;


public class Annonce {
    String idAnnonce;
    Voiture voiture;
    String description;
    Date date;
    double prix;
    int idClient;
    int status;
    double commission;

    // methods
    public void insert(Connection con) throws Exception{
                boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="INSERT INTO Couleur VALUES(DEFAULT, "+this.getVoiture().getIdVoiture()+", '"+ this.getDescription() +"'', default, "+ this.getPrix() +", "+ this.getIdClient() +", 1)";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
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

    public int getIdClient() {
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

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setVoiture(Voiture idVoiture) {
        this.voiture = idVoiture;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public Annonce() {
    }

}
