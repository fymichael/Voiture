package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.project.Voiture.model.connection.Connect;

public class Marque {
    String idMarque;
    String intitule;
    int etat;
    int nombreVente;

    public String getIdMarque(){
        return this.idMarque;
    }
    public void setIdMarque(String idMarque)throws Exception{
        this.idMarque=idMarque;
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
    
    public Marque()throws Exception{}
    public Marque(String id, String intitule, int etat)throws Exception{
        this.setIdMarque(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
    }
    public Marque(String id, String intitule, int etat, int nb)throws Exception{
        this.setIdMarque(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
        this.setNbVente(nb);
    }
    public Marque getById(Connection con)throws Exception{
        Marque marque= null;
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM marque WHERE id_marque='"+this.getIdMarque()+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                marque= new Marque(id, intitule, etat);
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
        return marque;
    }
    public Marque[] getAll(Connection con)throws Exception{
        Vector<Marque> listMarque= new Vector<Marque>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM marque WHERE etat=1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Marque m = new Marque(id, intitule, etat);
                listMarque.add(m);
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
        Marque[] marques= new Marque[listMarque.size()];
        listMarque.toArray(marques);
        return marques;
    }
    public Marque insert(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        ResultSet res=null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="INSERT INTO marque VALUES(DEFAULT, '"+this.getIntitule()+"', 1) returning id_marque";
            System.out.println(sql);
            res=stmt.executeQuery(sql);
            if(res.next()) this.setIdMarque(res.getString("id_marque"));
            System.out.println(this.getIdMarque());
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
            String sql="UPDATE marque SET intitule='"+this.getIntitule()+"' WHERE id_marque='"+this.getIdMarque()+"'";
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
            String sql="UPDATE marque SET etat=10 WHERE id_marque='"+this.getIdMarque()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }
    //Toutes les marques
    public static List<Marque> getAllMarque(Connection connection) throws Exception {
        List<Marque> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            wasConnected = false;
            connection = Connect.getConnection();
        } 
        String sql = "SELECT * FROM marque WHERE status > 0";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Marque model = new Marque();
                model.setIdMarque(rs.getString("id_marque"));
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

    // Marque la plus vendue
    public Marque getMarquePusVendue(Connection con)throws Exception{
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        Marque m=new Marque();
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM v_marque_plus_vendue";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString("id_marque");
                String intitule= result.getString("intitule");
                int etat=result.getInt("etat");
                int nb=result.getInt("nombres_ventes");
                m = new Marque(id, intitule, etat, nb);
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
        return m;
    }
}