package com.project.Voiture.model.backOffice.statistique;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.project.Voiture.model.connection.Connect;

public class Annonce {
    String idAnnonce;
    int idVoiture;
    String description;
    Date date;
    double prix;
    int idClient;
    int status;
    double commission;

    // methods
    // stat 6 : avoir les commission par mois
   /* public double[] getCommission(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        int nombre = 0;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT count(id_annonce) as nombre_annonce FROM annonce where status = 5";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                nombre = result.getInt("nombre_annonce");
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
        return nombre;
    }
*/
    // stat 5 : avoir la marque la plus vendues
    public String getBestMarque(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        String marque = "";

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "select marque, count(id_annonce) as nombre_annonce from v_detail_annonce where status_annonce = 10 group by marque order by nombre_annonce desc limit 1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                marque = result.getString("marque");
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
        return marque;
    }

    // stat 4 : avoir les repartitions des models pour le graphe pie
    public Vector<PieChartModel> getModelRepartition(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        Vector<PieChartModel> repartitionModel = new Vector<>();

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "select model, count(id_annonce) as nombre_annonce from v_detail_annonce where status_annonce = 10 group by model";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                PieChartModel pie = new PieChartModel();
                pie.setModel(result.getString("model"));
                pie.setPart(this.getPartValue(result.getDouble("nombre_annonce"), con));
                repartitionModel.add(pie);
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
        return repartitionModel;
    }

    // fonction pour avoir la vraie repartition des models
    public double getPartValue(double part, Connection con) throws Exception {
        return (part * this.getAnnonceVendus(con)) / 100;
    }

    // stat 3 : avoir le meilleur revendeur cad le client qui a le plus vendus grace
    // aux annonces
    public String getBestClient(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        String nom = "";
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "select nom, count(id_annonce) as annonces_vendues from v_annonce_client where status_client = 1 and status_annonce = 10 group by nom order by annonces_vendues desc limit 1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                nom = result.getString("nom");
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
        return nom;
    }

    // stat 2 : avoir le nombre d'annonces deja vendus
    public int getAnnonceVendus(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        int nombre = 0;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT count(id_annonce) as nombre_annonce FROM annonce where status = 10";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                nombre = result.getInt("nombre_annonce");
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
        return nombre;
    }

    // stat 1 : avoir le nombre d'annonces inscrits
    public int getAnnonceCourante(Connection con) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        int nombre = 0;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT count(id_annonce) as nombre_annonce FROM annonce where status = 5";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                nombre = result.getInt("nombre_annonce");
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
        return nombre;
    }

    //Toutes les annonces
    public static List<Annonce> getAllAnnonce(Connection connection) throws Exception {
        List<Annonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM annonce WHERE status > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Annonce model = new Annonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setDate(rs.getDate("date"));
                model.setPrix(rs.getDouble("prix"));
                model.setIdClient(rs.getInt("id_client"));
                model.setStatus(rs.getInt("status"));
                model.setCommission(rs.getDouble("commission"));
                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

     //Toutes les annonces d'une categorie
     public static List<Annonce> getAllAnnonceByCategorie(int idCategorie, Connection connection) throws Exception {
        List<Annonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM annonce WHERE status > 0 AND idCategorie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCategorie);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Annonce model = new Annonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setDate(rs.getDate("date"));
                model.setPrix(rs.getDouble("prix"));
                model.setIdClient(rs.getInt("id_client"));
                model.setStatus(rs.getInt("status"));
                model.setCommission(rs.getDouble("commission"));
                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }
    
    //Toutes les annonces d'une marque
    public static List<Annonce> getAllAnnonceByMarque(int idMarque, Connection connection) throws Exception {
        List<Annonce> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM annonce WHERE status > 0 AND idMarque = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMarque);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Annonce model = new Annonce();
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setDate(rs.getDate("date"));
                model.setPrix(rs.getDouble("prix"));
                model.setIdClient(rs.getInt("id_client"));
                model.setStatus(rs.getInt("status"));
                model.setCommission(rs.getDouble("commission"));
                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    //Annonce par son id 
    public static Annonce findById(int id, Connection connection) throws Exception {
        Annonce model = null;
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM annonce WHERE id_annonce = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                model.setIdAnnonce(rs.getString("id_annonce"));
                model.setIdVoiture(rs.getInt("id_voiture"));
                model.setDescription(rs.getString("description"));
                model.setDate(rs.getDate("date"));
                model.setPrix(rs.getDouble("prix"));
                model.setIdClient(rs.getInt("id_client"));
                model.setStatus(rs.getInt("status"));
                model.setCommission(rs.getDouble("commission"));
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return model;
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

    public int getIdVoiture() {
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

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
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
