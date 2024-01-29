package com.project.Voiture.model.frontOffice.listeAnnonce.views;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import lombok.*;
import java.time.LocalDate;
import java.sql.*;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class VAnnonce {
    private String idAnnonce;
    private String idVoiture;
    private String description;
    private String idProfil;
    private LocalDate dateAnnonce;
    private Double prix;
    private Integer status;
    private String idMarque;
    private String marque;
    private String idCategorie;
    private String categorie;
    private String modele;
    private String idEnergie;
    private String energie;
    private String idCouleur;
    private String couleur;
    private String idModeTransmission;
    private String modeTransmission;
    private String idLieu;
    private String lieu;
    private String anneeSortie;
    private String immatriculation;
    private Double autonomie;
    private Integer nbPorte;
    private Integer nbSiege;
    private String idSpecification;
    private String specification;
    private Double kilometrage;

///Fonctions
     //Toutes les annonces
     public static List<VAnnonce> findAll(Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

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
        if(feature.equals("modele")) {
            return "id_modele";
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
    public static List<VAnnonce> findByFeatures(String feature, String idFeature, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String featureValue = VAnnonce.getIdFeature(feature);
       
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 AND "+featureValue+" = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idFeature);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    //Une annonce par son id
    public static VAnnonce findOneById(String id, Connection connection) throws Exception {
        VAnnonce model = new VAnnonce();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
       
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 AND id_annonce = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return model;
    }

    //Recheeche par prix
    public static List<VAnnonce> findByPrix(String prixMin, String prixMax, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if(prixMin.trim().equals("") || prixMin == null) {
            throw new Exception("Valeur prix ne doit pas etre null");
        }
        if(prixMax.trim().equals("") || prixMax == null) {
            throw new Exception("Valeur prix ne doit pas etre null");
        }
        Double prixMinParsed = 0.0;
        Double prixMaxParsed = 0.0;
        try {
            prixMinParsed = Double.valueOf(prixMin);
            prixMaxParsed = Double.valueOf(prixMax);
        } catch(Exception e) {
            throw new Exception("Prix doit etre un nombre");
        }
        if(prixMinParsed < 0 || prixMaxParsed <0) {
            throw new Exception("Prix doit etre positive");
        }

        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 AND prix >= ? AND prix <= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, prixMinParsed);
            stmt.setDouble(2, prixMaxParsed);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

     //Recheeche par date annonce
     public static List<VAnnonce> findByDateAnnonce(String dateMin, String dateMax, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if(dateMin.trim().equals("") || dateMin == null) {
            throw new Exception("Valeur date ne doit pas etre null");
        }
        if(dateMax.trim().equals("") || dateMax == null) {
            throw new Exception("Valeur date ne doit pas etre null");
        }

        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 AND date_annonce >= ? AND date_annonce <= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(dateMin));
            stmt.setDate(2, Date.valueOf(dateMax));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

     //Recheche par mot cle
     public static List<VAnnonce> findByKeyWord(String keyWord, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if(keyWord.trim().equals("") || keyWord == null) {
            throw new Exception("Les valeurrs des champs ne doivent pas etre null");
        }

        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 AND description like ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%"+keyWord+"%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    //Former la requete sql
    public static String getRequestSql(HttpServletRequest request) throws Exception {
        String requestSql = "";
        if(request.getParameter("dateMin") != null && request.getParameter("dateMax") != null) {
            requestSql = requestSql + " AND date_min >= '"+request.getParameter("dateMin")+"' AND date_annonce <= '"+request.getParameter("dateMax")+"'";
        }
        if(request.getParameter("prixMin") != null && request.getParameter("prixMax") != null) {
            requestSql = requestSql + " AND prix >= "+request.getParameter("prixMin")+" AND prix <= "+request.getParameter("prixMax");
        }
        if(request.getParameter("categorie") != null) {
            requestSql = requestSql + " AND id_categorie = '"+request.getParameter("categorie")+"'";
        }
        if(request.getParameter("marque") != null) {
            requestSql = requestSql + " AND id_marque = '"+request.getParameter("marque")+"'";
        }
        if(request.getParameter("lieu") != null) {
            requestSql = requestSql + " AND id_lieu = '"+request.getParameter("lieu")+"'";
        }
        if(request.getParameter("mode_tranmsission") != null) {
            requestSql = requestSql + " AND id_mode_transmission = '"+request.getParameter("mode_transmission")+"'";
        }
        if(request.getParameter("energie") != null) {
            requestSql = requestSql + " AND id_energie = '"+request.getParameter("energie")+"'";
        }
        return requestSql;
    } 

    //Recheche multicritere
     public static List<VAnnonce> findByMultiCritere(HttpServletRequest request, Connection connection) throws Exception {
        List<VAnnonce> models = new ArrayList<>();
        boolean wasConnected = true;
        
        String requestFilter = VAnnonce.getRequestSql(request);
        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM v_detail_annonce WHERE status_annonce > 0 "+requestFilter;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VAnnonce model = new VAnnonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getString("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setIdProfil(rs.getString("id_profil"));
                model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                model.setPrix(rs.getDouble("prix"));
                model.setStatus(rs.getInt("status_annonce"));
                model.setIdMarque(rs.getString("id_marque"));
                model.setMarque(rs.getString("marque"));
                model.setIdCategorie(rs.getString("id_categorie"));
                model.setCategorie(rs.getString("categorie"));
                model.setModele(rs.getString("model"));
                model.setIdEnergie(rs.getString("id_energie"));
                model.setEnergie(rs.getString("energie"));
                model.setIdCouleur(rs.getString("id_couleur"));
                model.setCouleur(rs.getString("couleur"));
                model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                model.setModeTransmission(rs.getString("mode_transmission"));
                model.setIdLieu(rs.getString("id_lieu"));
                model.setLieu(rs.getString("lieu"));
                model.setAnneeSortie(rs.getString("anne_sortie"));
                model.setImmatriculation(rs.getString("immatriculation"));
                model.setAutonomie(rs.getDouble("autonomie"));
                model.setNbPorte(rs.getInt("nb_porte"));
                model.setNbSiege(rs.getInt("nb_siege"));
                model.setIdSpecification(rs.getString("id_specification"));
                model.setSpecification(rs.getString("specification"));
                model.setKilometrage(rs.getDouble("kilometrage"));

                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

         //Toutes les annonces non valider
         public static List<VAnnonce> getNonValider(Connection connection) throws Exception {
            List<VAnnonce> models = new ArrayList<>();
            boolean wasConnected = true;
            if (connection == null) {
                wasConnected = false;
                connection = Connect.connectDB();
            } 
            String sql = "SELECT * FROM v_annonce WHERE status = 1";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                System.out.println(stmt.toString());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    VAnnonce model = new VAnnonce();
                    model.setIdAnnonce(rs.getString("id_annonce"));
                    model.setIdVoiture(rs.getString("id_voiture"));
                    model.setDescription(rs.getString("description"));
                    model.setIdProfil(rs.getString("id_profil"));
                    model.setDateAnnonce(LocalDate.parse(rs.getDate("date_annonce").toString()));
                    model.setPrix(rs.getDouble("prix"));
                    model.setStatus(rs.getInt("status_annonce"));
                    model.setIdMarque(rs.getString("id_marque"));
                    model.setMarque(rs.getString("marque"));
                    model.setIdCategorie(rs.getString("id_categorie"));
                    model.setCategorie(rs.getString("categorie"));
                    model.setModele(rs.getString("model"));
                    model.setIdEnergie(rs.getString("id_energie"));
                    model.setEnergie(rs.getString("energie"));
                    model.setIdCouleur(rs.getString("id_couleur"));
                    model.setCouleur(rs.getString("couleur"));
                    model.setIdModeTransmission(rs.getString("id_mode_transmission"));
                    model.setModeTransmission(rs.getString("mode_transmission"));
                    model.setIdLieu(rs.getString("id_lieu"));
                    model.setLieu(rs.getString("lieu"));
                    model.setAnneeSortie(rs.getString("anne_sortie"));
                    model.setImmatriculation(rs.getString("immatriculation"));
                    model.setAutonomie(rs.getDouble("autonomie"));
                    model.setNbPorte(rs.getInt("nb_porte"));
                    model.setNbSiege(rs.getInt("nb_siege"));
                    model.setIdSpecification(rs.getString("id_specification"));
                    model.setSpecification(rs.getString("specification"));
                    model.setKilometrage(rs.getDouble("kilometrage"));
    
                    models.add(model);
                }
            }
    
            if (!wasConnected) {
                connection.close();
            }
            return models;
        }
    
}
