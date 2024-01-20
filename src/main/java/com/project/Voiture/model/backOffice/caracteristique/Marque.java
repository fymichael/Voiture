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
    Integer idMarque;
    String intitule;
    int etat;

    public Integer getIdMarque(){
        return this.idMarque;
    }
    public void setIdMarque(Integer idMarque)throws Exception{
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
    public Marque()throws Exception{}
    public Marque(Integer id, String intitule, int etat)throws Exception{
        this.setIdMarque(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
    }

    public void insert(String intitule,  Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            this.setIntitule(intitule);
            String sql="INSERT INTO Marque VALUES(DEFAULT, '"+this.getIntitule()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
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
            String sql = "SELECT * FROM Marque where status != 10 ";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                Integer id= result.getInt(1);
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

    public void update(Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectDB();
                valid=false;
            } 
            stmt= con.createStatement();
            String sql="UPDATE Marque SET intitule='"+this.getIntitule()+"' WHERE id_marque='"+this.getIdMarque()+"'";
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
            String sql="UPDATE Marque SET etat=10 WHERE id_marque='"+this.getIdMarque()+"'";
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
                model.setIdMarque(rs.getInt("id_marque"));
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