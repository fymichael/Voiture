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
    String idProfil;
    int status;

    // methods
    // modifier une annonce
    public void changePrice(Connection con, String idAnnonce) throws Exception {
        boolean valid = true;
        PreparedStatement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "UPDATE Annonce SET prix = ? WHERE idAnnonce = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setDouble(1, this.getPrix());
            pstmt.setString(5, idAnnonce);

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

    // fonction pour marquer une annonce deja vendus
    public void vendre(Connection con, String idAnnonce, Date dateVente) throws Exception {
        if (dateVente.after(this.getDate())) {
            boolean valid = true;
            Statement pstmt = null;

            try {
                if (con == null) {
                    con = Connect.connectDB();
                    valid = false;
                }

                String sql = "insert into vente values (default, '" + idAnnonce + "', " + dateVente + ")";
                System.out.println(sql);
                pstmt = con.createStatement();
                pstmt.executeUpdate(sql);

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
        } else {
            throw new Exception(" La date de vente ne doit pas etre anterieur a la date d'ajout de l'annonce ");
        }
    }
    

    // suppression d'une annonce
    public void delete(Connection con, String idAnnonce) throws Exception {
        boolean valid = true;
        Statement pstmt = null;

        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = "update annonce set status = 0 where id_annonce = '" + idAnnonce + "'";
            pstmt = con.createStatement();
            pstmt.executeUpdate(sql);

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

    // avoir une annonce par son id
    public Annonce getById(String idAnnonce, Connection con) throws Exception {
        boolean valid = true;
        Statement pstmt = null;
        ResultSet rs = null;

        Annonce a = new Annonce();
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select * from annonce where id_annonce = '" + idAnnonce + "'";
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                a.setIdProfil(rs.getString("id_profil"));
                a.setIdVoiture(rs.getString("id_voiture"));
                a.setDate(rs.getDate("date"));
                a.setDescription(rs.getString("description"));
                a.setIdAnnonce(rs.getString("id_annonce"));
                a.setPrix(rs.getDouble("prix"));
                a.setStatus(rs.getInt("status"));
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
        return a;
    }

    // toutes les annonces non valider
    public Vector<Annonce> AnnonceNonValider(Connection con) throws Exception {
        boolean valid = true;
        Statement pstmt = null;
        ResultSet rs = null;

        Vector<Annonce> annonces = new Vector<>();
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select * from annonce where status = 1";
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setIdProfil(rs.getString("id_profil"));
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

    // avoir toutes les annonces du client connecter
    public Vector<Annonce> clientAnnonces(String idProfil, Connection con) throws Exception {
        boolean valid = true;
        Statement pstmt = null;
        ResultSet rs = null;

        Vector<Annonce> annonces = new Vector<>();
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select * from annonce where id_profil = '" + idProfil + "'";
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setIdProfil(rs.getString("id_profil"));
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

            String sql = "INSERT INTO Annonce (id_voiture, description, prix, id_profil) VALUES( ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, this.getIdVoiture());
            pstmt.setString(2, this.getDescription());
            pstmt.setDouble(3, this.getPrix());
            pstmt.setString(4, this.getIdProfil());

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

    // validation annonce
    public void validation(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="UPDATE annonce SET status=5 WHERE id_annonce='"+this.getIdAnnonce()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }

    public int getNbAnnoncesVendus(Connection con) throws Exception {
        boolean valid = true;
        Statement pstmt = null;
        ResultSet rs = null;

        int count=0;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }

            String sql = " select count(*) from vente";
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                count=rs.getInt(1);
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
        return count;
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

    public String getIdProfil() {
        return idProfil;
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

    public void setIdProfil(String idProfil) {
        this.idProfil = idProfil;
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
