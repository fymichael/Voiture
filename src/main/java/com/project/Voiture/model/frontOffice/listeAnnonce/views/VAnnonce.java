package com.project.Voiture.model.frontOffice.listeAnnonce.views;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;
import java.time.LocalDate;
import java.sql.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class VAnnonce {
    private Integer idAnnonce;
    private Integer idVoiture;
    private String description;
    private Integer idProfil;
    private LocalDate dateAnnonce;
    private Double prix;
    private Double commission;
    private Integer status;
    private Integer idMarque;
    private String marque;
    private Integer idCategorie;
    private String categorie;
    private Integer idSpecification;
    private String specification;
    private Integer idEnergie;
    private String energie;
    private Integer idCouleur;
    private String couleur;
    private Integer idModeTransmission;
    private String modeTransmission;
    private Integer idLieu;
    private String lieu;
    private String anneeSortie;
    private String immatriculation;
    private Double autonomie;
    private String specificationPlus;
    private Integer nbPorte;
    private Integer nbSiege;

///Fonctions
     //Toutes les annonces
     public static List<VAnnonce> findAll(Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM v_annonce WHERE status > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getInt("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getInt("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setCommission(rs.getDouble("commission"));
                model.setStatus(rs.getInt("status"));
                model.setIdMarque(rs.getInt("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getInt("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setIdSpecification(rs.getInt("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setIdEnergie(rs.getInt("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getInt("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getInt("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getInt("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("annee_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setSpecificationPlus(rs.getString("specification_plus"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    //Avoir la lettre id du valeur donnee
    public static String getIdFeature(String feature) throws Exception {
        if(feature.equals("categorie")) {
            return "id_categorie";
        }
        if(feature.equals("marque")) {
            return "id_marque";
        }
        if(feature.equals("specification")) {
            return "id_specification";
        }
        if(feature.equals("lieu")) {
            return "id_lieu";
        }
        if(feature.equals("mode_transmission")) {
            return "id_mode_transmission";
        }
        if(feature.equals("energie")) {
            return "id_energie";
        }

        throw new Exception("Erreur : Impossible de trouver le caractere correspondant a "+feature);
    }

    //Toutes les annonces filtres par categorie
    public static List<VAnnonce> findByFeatures(String feature, int idFeature, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String featureValue = VAnnonce.getIdFeature(feature);
       
        String sql = "SELECT * FROM v_annonce WHERE status > 0 AND "+featureValue+" = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFeature);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getInt("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getInt("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setCommission(rs.getDouble("commission"));
                model.setStatus(rs.getInt("status"));
                model.setIdMarque(rs.getInt("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getInt("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setIdSpecification(rs.getInt("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setIdEnergie(rs.getInt("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getInt("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getInt("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getInt("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("annee_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setSpecificationPlus(rs.getString("specification_plus"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    //Une annonce par son id
    public static VAnnonce findOneById(int id, Connection connection) throws Exception {
        VAnnonce model = new VAnnonce();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
       
        String sql = "SELECT * FROM v_annonce WHERE status > 0 AND id_annonce = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.setIdAnnonce(rs.getInt("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getInt("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setCommission(rs.getDouble("commission"));
                model.setStatus(rs.getInt("status"));
                model.setIdMarque(rs.getInt("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getInt("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setIdSpecification(rs.getInt("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setIdEnergie(rs.getInt("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getInt("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getInt("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getInt("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("annee_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setSpecificationPlus(rs.getString("specification_plus"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return model;
    }

     //Toutes les annonces
     public static List<VAnnonce> getNonValider(Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM v_annonce WHERE status = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getInt("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getInt("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status"));
                model.setIdMarque(rs.getInt("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getInt("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setIdSpecification(rs.getInt("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setIdEnergie(rs.getInt("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getInt("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getInt("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getInt("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }
}
