package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Voiture {
    int idVoiture;
    Marque marque;
    Categorie categorie;
    Modele modele;
    Energie energie;
    Couleur couleur;
    String anneeSortie;
    String immatriculation;
    double autonomie;
    ModeTransmission modeTransmission;

    // methods
    public Voiture[] getAll(Connection con) throws Exception {
        Vector<Voiture> listVoiture = new Vector<Voiture>();
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Voiture ";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                Voiture v = new Voiture();
                this.setIdVoiture(result.getInt("id_voiture"));
                Marque marque = new Marque().getById(con, result.getInt("id_marque"));
                Categorie categorie = new Categorie().getById(con, result.getInt("id_categorie"));
                Modele modele = new Modele().getById(con, result.getInt("id_modele"));
                Energie energie = new Energie().getById(con, result.getInt("id_energie"));
                Couleur couleur = new Couleur().getById(con, result.getInt("id_couleur"));
                ModeTransmission modeTransmission = new ModeTransmission().getById(con, result.getInt("id_mode_transformation"));
                v.setCategorie(categorie);
                v.setCouleur(couleur);
                v.setEnergie(energie);
                v.setModele(modele);
                v.setMarque(marque);
                v.setModeTransmission(modeTransmission);
                v.setAnneeSortie(result.getString("annee_sortie"));
                v.setAutonomie(result.getDouble("autonomie"));
                v.setImmatriculation(result.getString("immatriculation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (state != null) {
                    state.close();
                }
                if (result != null) {
                    result.close();
                }
                if (valid == false || con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Voiture[] Voitures = new Voiture[listVoiture.size()];
        listVoiture.toArray(Voitures);
        return Voitures;
    }

    // constructor
    public Voiture() {
    }

    public ModeTransmission getModeTransmission() {
        return modeTransmission;
    }

    public void setModeTransmission(ModeTransmission modeTransmission) {
        this.modeTransmission = modeTransmission;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }

    public double getAutonomie() {
        return autonomie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Energie getEnergie() {
        return energie;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public void setEnergie(Energie energie) {
        this.energie = energie;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setAutonomie(double autonomie) {
        this.autonomie = autonomie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public Modele getModele() {
        return modele;
    }

    public Marque getMarque() {
        return marque;
    }

    public int getIdVoiture() {
        return idVoiture;
    }
}
