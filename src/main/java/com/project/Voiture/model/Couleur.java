package com.project.Voiture.model;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

public class Couleur {
    String idCouleur;
    String intitule;
    int etat;

    public String getIdCouleur(){
        return this.idCouleur;
    }
    public void setIdCouleur(String idCouleur)throws Exception{
        this.idCouleur=idCouleur;
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

    public Couleur()throws Exception{}
    public Couleur(String id, String intitule, int etat)throws Exception{
        this.setIdCouleur(id);
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
            this.setEtat(etat);
            String sql="INSERT INTO Couleur VALUES(DEFAULT, '"+this.getIntitule()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
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
            String sql="UPDATE  Couleur SET intitule='"+this.getIntitule()+"' WHERE id_couleur='"+this.getIdCouleur()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }

    public Couleur[] getAll(Connection con)throws Exception{
        Vector<Couleur> listCouleur= new Vector<Couleur>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM Couleur where etat != 10";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Couleur m = new Couleur(id, intitule, etat);
                listCouleur.add(m);
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
        Couleur[] couleurs= new Couleur[listCouleur.size()];
        listCouleur.toArray(couleurs);
        return couleurs;
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
            String sql="UPDATE Couleur SET etat=10 WHERE id_couleur='"+this.getIdCouleur()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }
}