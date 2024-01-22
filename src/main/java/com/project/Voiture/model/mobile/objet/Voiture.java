package com.project.Voiture.model.mobile.objet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Voiture {
    String idVoiture;
    String idMarque;
    String idCategorie;
    String idSpecification;
    String idEnergie;
    String idCouleur;
    String anneeSortie;
    String immatriculation;
    double autonomie;
    String idModeTransmission;
    String[] photos;

    // methods
    // modifier une voiture
    public void update(Connection con, String idVoiture) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = " update voiture set id_marque = '" + this.getIdMarque() + "', id_categorie = '"
                    + this.getIdCategorie() + "', id_modele = '" + this.getIdSpecification() + "', id_energie = '"
                    + this.getIdEnergie() + "', id_couleur = '" + this.getIdCouleur() + "', anne_sortie = '" + this.getAnneeSortie() + "', immatriculation = '"
                    + this.getImmatriculation() + "', autonomie = " + this.getAutonomie() + ", id_mode_transmission = '" + this.getIdModeTransmission()
                    + "', status = 1 where id_voiture = "+ idVoiture +")";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            Voiture lastVoiture = this.lastVoiture(null);
            for (String photo : photos) {
                String sql1 = "insert into voiture_photo values ('" + lastVoiture.getIdVoiture() + "', '" + photo
                        + "')";
                System.out.println(sql1);
                stmt.executeUpdate(sql1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

    // inserer une nouvelle voiture
    public void insert(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "INSERT INTO Voiture VALUES(DEFAULT, '" + this.getIdMarque() + "', '"
                    + this.getIdCategorie() + "', '" + this.getIdSpecification() + "', '"
                    + this.getIdEnergie() + "', '" + this.getIdCouleur() + "', '" + this.getAnneeSortie() + "', '"
                    + this.getImmatriculation() + "'," + this.getAutonomie() + ", '" + this.getIdModeTransmission()
                    + "', 1)";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            Voiture lastVoiture = this.lastVoiture(null);
            for (String photo : photos) {
                String sql1 = "insert into voiture_photo values ('" + lastVoiture.getIdVoiture() + "', '" + photo + "')";
                System.out.println(sql1);
                stmt.executeUpdate(sql1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

    // avoir le dernier voiture inserer
    public Voiture lastVoiture(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        Voiture v = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Voiture order by id_voiture desc limit 1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                v = new Voiture();
                v.setIdVoiture(result.getString("id_voiture"));
                v.setIdCategorie(result.getString("id_categorie"));
                v.setIdCouleur(result.getString("id_couleur"));
                v.setIdEnergie(result.getString("id_energie"));
                v.setIdSpecificationgetIdSpecification(result.getString("id_modele"));
                v.setIdMarque(result.getString("id_marque"));
                v.setIdModeTransmission(result.getString("id_mode_transmission"));
                v.setAnneeSortie(result.getString("anne_sortie"));
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
        return v;
    }

    // avoir les voitures par son id
    public Voiture getById(Connection con, String idVoiture) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        Voiture v = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Voiture where status != 0 and id_voiture = '" + idVoiture + "'";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                v = new Voiture();
                v.setIdVoiture(result.getString("id_voiture"));
                v.setIdCategorie(result.getString("id_categorie"));
                v.setIdCouleur(result.getString("id_couleur"));
                v.setIdEnergie(result.getString("id_energie"));
                v.setIdSpecificationgetIdSpecification(result.getString("id_modele"));
                v.setIdMarque(result.getString("id_marque"));
                v.setIdModeTransmission(result.getString("id_mode_transmission"));
                v.setAnneeSortie(result.getString("anne_sortie"));
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
        return v;
    }

    // avoir toutes les voitures
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
            String sql = "SELECT * FROM Voiture where status != 0";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                Voiture v = new Voiture();
                v.setIdVoiture(result.getString("id_voiture"));
                v.setIdCategorie(result.getString("id_categorie"));
                v.setIdCouleur(result.getString("id_couleur"));
                v.setIdEnergie(result.getString("id_energie"));
                v.setIdSpecificationgetIdSpecification(result.getString("id_modele"));
                v.setIdMarque(result.getString("id_marque"));
                v.setIdModeTransmission(result.getString("id_mode_transmission"));
                v.setAnneeSortie(result.getString("anne_sortie"));
                v.setAutonomie(result.getDouble("autonomie"));
                v.setImmatriculation(result.getString("immatriculation"));
                listVoiture.add(v);
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

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public void setIdSpecificationgetIdSpecification(String idSpecification) {
        this.idSpecification = idSpecification;
    }

    public void setIdModeTransmission(String idModeTransmission) {
        this.idModeTransmission = idModeTransmission;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public void setIdEnergie(String idEnergie) {
        this.idEnergie = idEnergie;
    }

    public void setIdCouleur(String idCouleur) {
        this.idCouleur = idCouleur;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setAutonomie(double autonomie) {
        this.autonomie = autonomie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getIdVoiture() {
        return idVoiture;
    }

    public String getIdSpecification() {
        return idSpecification;
    }

    public String getIdModeTransmission() {
        return idModeTransmission;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public String getIdEnergie() {
        return idEnergie;
    }

    public String getIdCouleur() {
        return idCouleur;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public double getAutonomie() {
        return autonomie;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }
}
