package com.project.Voiture.model.mobile.objet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    String modele;
    int porte;
    int siege;
    double kilometrage;
    String idLieu;

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
                    + this.getIdCategorie() + "', id_specification = '" + this.getIdSpecification() + "', id_energie = '"
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
                    + "', 1, '"+ this.getModele() +"', "+ this.getPorte() +", "+ this.getSiege() +", "+ this.getKilometrage() +", '"+ this.getIdLieu() + "')";
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
                v.setIdSpecificationgetIdSpecification(result.getString("id_specification"));
                v.setIdMarque(result.getString("id_marque"));
                v.setIdModeTransmission(result.getString("id_mode_transmission"));
                v.setAnneeSortie(result.getString("anne_sortie"));
                v.setAutonomie(result.getDouble("autonomie"));
                v.setImmatriculation(result.getString("immatriculation"));
                v.setModele(result.getString("model"));
                v.setPorte(result.getInt("nb_porte"));
                v.setSiege(result.getInt("nb_siege"));
                v.setKilometrage(result.getDouble("kilometrage"));
                v.setIdLieu(result.getString("id_lieu"));
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
    Voiture v = null;
    try {
        if (con == null) {
            con = Connect.connectDB();
        }
        String sql = "SELECT * FROM Voiture WHERE status != 0 AND id_voiture = ?";
        String sql1 = "SELECT * FROM voiture_photo WHERE id_voiture = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             PreparedStatement ps1 = con.prepareStatement(sql1)) {
            ps.setString(1, idVoiture);
            ps1.setString(1, idVoiture);
            
            try (ResultSet result = ps.executeQuery();
                 ResultSet result1 = ps1.executeQuery()) {
                
                List<String> photos = new ArrayList<>();
                
                v = new Voiture();
                while (result.next() && result1.next()) {
                    v.setIdVoiture(result.getString("id_voiture"));
                    v.setIdCategorie(result.getString("id_categorie"));
                    v.setIdCouleur(result.getString("id_couleur"));
                    v.setIdEnergie(result.getString("id_energie"));
                    v.setIdSpecificationgetIdSpecification(result.getString("id_specification"));
                    v.setIdMarque(result.getString("id_marque"));
                    v.setIdModeTransmission(result.getString("id_mode_transmission"));
                    v.setAnneeSortie(result.getString("anne_sortie"));
                    v.setAutonomie(result.getDouble("autonomie"));
                    v.setImmatriculation(result.getString("immatriculation"));
                    v.setModele(result.getString("model"));
                    v.setPorte(result.getInt("nb_porte"));
                    v.setSiege(result.getInt("nb_siege"));
                    v.setKilometrage(result.getDouble("kilometrage"));
                    v.setIdLieu(result.getString("id_lieu"));
                    photos.add(result1.getString("photo"));
                }
                v.setPhotos(photos.toArray(new String[0]));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (con != null) {
            con.close();
        }
    }
    return v;
}

public Voiture[] getAvailableCarByClient(String idClient, Connection con) throws Exception {
    Vector<Voiture> listVoiture = new Vector<Voiture>();
    boolean valid = true;
    Statement state = null;
    ResultSet result = null;
    try {
        if (con == null) {
            con = Connect.connectDB();
            valid = false;
        }
        String sql = "SELECT * FROM v_annonce where status != 0 and id_profil = '"+ idClient +"' and id_voiture not in (select id_voiture from annonce where status !=0)";
        System.out.println(sql);
        state = con.createStatement();
        result = state.executeQuery(sql);
        while (result.next()) {
            Voiture v = new Voiture();
            v.setIdVoiture(result.getString("id_voiture"));
            v.setIdCategorie(result.getString("id_categorie"));
            v.setIdCouleur(result.getString("id_couleur"));
            v.setIdEnergie(result.getString("id_energie"));
            v.setIdSpecificationgetIdSpecification(result.getString("id_specification"));
            v.setIdMarque(result.getString("id_marque"));
            v.setIdModeTransmission(result.getString("id_mode_transmission"));
            v.setAnneeSortie(result.getString("anne_sortie"));
            v.setAutonomie(result.getDouble("autonomie"));
            v.setImmatriculation(result.getString("immatriculation"));
            v.setModele(result.getString("model"));
            v.setPorte(result.getInt("nb_porte"));
            v.setSiege(result.getInt("nb_siege"));
            v.setKilometrage(result.getDouble("kilometrage"));
            v.setIdLieu(result.getString("id_lieu"));
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
                v.setIdSpecificationgetIdSpecification(result.getString("id_specification"));
                v.setIdMarque(result.getString("id_marque"));
                v.setIdModeTransmission(result.getString("id_mode_transmission"));
                v.setAnneeSortie(result.getString("anne_sortie"));
                v.setAutonomie(result.getDouble("autonomie"));
                v.setImmatriculation(result.getString("immatriculation"));
                v.setModele(result.getString("model"));
                v.setPorte(result.getInt("nb_porte"));
                v.setSiege(result.getInt("nb_siege"));
                v.setKilometrage(result.getDouble("kilometrage"));
                v.setIdLieu(result.getString("id_lieu"));
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

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }
    public void setIdSpecification(String idSpecification) {
        this.idSpecification = idSpecification;
    }
    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }
    public int getSiege() {
        return siege;
    }
    public int getPorte() {
        return porte;
    }
    public String getModele() {
        return modele;
    }
    public double getKilometrage() {
        return kilometrage;
    }
    public String getIdLieu() {
        return idLieu;
    }
}
