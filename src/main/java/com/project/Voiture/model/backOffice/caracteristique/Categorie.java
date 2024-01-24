package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.project.Voiture.model.connection.Connect;

public class Categorie {
    Integer idCategorie;
    String intitule;
    int etat;

    public Integer getIdCategorie(){
        return this.idCategorie;
    }
    public void setIdCategorie(Integer idCategorie)throws Exception{
        this.idCategorie=idCategorie;
    }

    public String getIntitule(){
        return this.intitule;
    }
    public void setIntitule(String intitule)throws Exception{
        if(intitule.length()==0){
            throw new Exception("intitule nulle");
        }
        this.intitule=intitule;
    }
    public int getEtat(){
        return this.etat;
    }

    public void setEtat(int etat)throws Exception{
        if(etat<0){
            throw new Exception("etat invalide: negatif");
        }
        this.etat=etat;
    }
    public void setEtat(String etat)throws Exception{
        int a =Integer.valueOf(etat);
        if(etat.length()==0){
            throw new Exception("etat invalide: null");
        }
        this.setEtat(a);
    }

    public Categorie()throws Exception{}
    public Categorie(Integer id, String intitule, int etat)throws Exception{
        this.setIdCategorie(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
    }

    public void insert(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            this.setIntitule(intitule);
            
            String sql="INSERT INTO Categorie VALUES(DEFAULT, '"+this.getIntitule()+"', 1)";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }

    public Categorie[] getAll(Connection con)throws Exception{
        Vector<Categorie> listCategorie= new Vector<Categorie>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM Categorie ";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                Integer id= result.getInt(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Categorie m = new Categorie(id, intitule, etat);
                listCategorie.add(m);
            }
        } catch (Exception e) {   
            e.printStackTrace(); 
        }finally{
            try {
                if(state!=null ){ state.close(); }
                if(result!=null ){ result.close(); }
                if(valid==false || con !=null){ con.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Categorie[] categories= new Categorie[listCategorie.size()];
        listCategorie.toArray(categories);
        return categories;
    }

    public void update(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="UPDATE  Categorie SET intitule='"+this.getIntitule()+"' WHERE id_categorie='"+this.getIdCategorie()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }

    public void delete(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="UPDATE  Categorie SET status=10 WHERE id_categorie='"+this.getIdCategorie()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }

    //Toutes les categories
    public static List<Categorie> getAllCategorie(Connection connection) throws Exception {
        List<Categorie> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM categorie WHERE status > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categorie model = new Categorie();
                model.setIdCategorie(rs.getInt("id_categorie"));
                model.setIntitule(rs.getString("intitule"));
                model.setEtat(rs.getInt("status"));
                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }
}