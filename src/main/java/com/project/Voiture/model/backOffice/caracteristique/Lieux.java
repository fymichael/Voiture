package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.project.Voiture.model.connection.Connect;

public class Lieux {
    String idLieux;
    String intitule;
    int etat;
    int nombreVente;

    public String getIdLieux(){
        return this.idLieux;
    }
    public void setIdLieux(String idLieux)throws Exception{
        this.idLieux=idLieux;
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
    public int getNbVente(){
        return this.nombreVente;
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
    public void setNbVente(int nombreVente)throws Exception{
        if(nombreVente<0){
            throw new Exception("nombreVente invalide: negatif");
        }
        this.nombreVente=nombreVente;
    }
    public void setNbVente(String nombreVente)throws Exception{
        int a =Integer.valueOf(nombreVente);
        if(nombreVente.length()==0){
            throw new Exception("nombreVente invalide: null");
        }
        this.setNbVente(a);
    }
    
    public Lieux()throws Exception{}

    // public Lieux(String id, String intitule, int etat)throws Exception{
    //     this.setIdLieux(id);
    //     this.setIntitule(intitule);
    //     this.setEtat(etat);
    // }
    public Lieux(String id, String intitule,  int nb)throws Exception{
        this.setIdLieux(id);
        this.setIntitule(intitule);
        this.setNbVente(nb);
    }
    public Lieux(String id, String intitule, int etat, int nb)throws Exception{
        this.setIdLieux(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
        this.setNbVente(nb);
    }
    public Lieux getById(Connection con)throws Exception{
        Lieux Lieux= null;
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM Lieux WHERE id_Lieux='"+this.getIdLieux()+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Lieux= new Lieux(id, intitule, etat);
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
        return Lieux;
    }
    public Lieux[] getAll(Connection con)throws Exception{
        Vector<Lieux> listLieux= new Vector<Lieux>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM Lieux WHERE etat=1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Lieux m = new Lieux(id, intitule, etat);
                listLieux.add(m);
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
        Lieux[] Lieuxs= new Lieux[listLieux.size()];
        listLieux.toArray(Lieuxs);
        return Lieuxs;
    }
    public Lieux insert(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        ResultSet res=null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="INSERT INTO Lieux VALUES(DEFAULT, '"+this.getIntitule()+"', 1) returning id_Lieux";
            System.out.println(sql);
            res=stmt.executeQuery(sql);
            if(res.next()) this.setIdLieux(res.getString("id_Lieux"));
            System.out.println(this.getIdLieux());
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
        return this;
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
            String sql="UPDATE Lieux SET intitule='"+this.getIntitule()+"' WHERE id_Lieux='"+this.getIdLieux()+"'";
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
            String sql="UPDATE Lieux SET etat=10 WHERE id_Lieux='"+this.getIdLieux()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }
    //Toutes les Lieuxs
    public static List<Lieux> getAllLieux(Connection connection) throws Exception {
        List<Lieux> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.connectDB();
        } 
        String sql = "SELECT * FROM Lieux WHERE etat > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lieux model = new Lieux();
                model.setIdLieux(rs.getString("id_Lieux"));
                model.setIntitule(rs.getString("intitule"));
                model.setEtat(rs.getInt("etat"));
                models.add(model);
            }
        }

        if (!wasConnected) {
            connection.close();
        }
        return models;
    }

    // Lieux la plus vendue
    public Lieux[] getLieuxPusVendue(Connection con)throws Exception{
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        Lieux m=new Lieux();
        Vector<Lieux> liste=new Vector<Lieux>();
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM v_Lieux_plus_vendue";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString("id_Lieux");
                String intitule= result.getString("intitule");
                int nb=result.getInt("nombre_ventes");
                m = new Lieux(id, intitule, nb);
                liste.add(m);
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
        Lieux[] Lieuxs= new Lieux[liste.size()];
        liste.toArray(Lieuxs);
        return Lieuxs;
    }
}