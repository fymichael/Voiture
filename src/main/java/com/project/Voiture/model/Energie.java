package com.project.Voiture.model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

public class Energie {
    String idEnergie;
    String intitule;
    int etat;

    public String getIdEnergie(){
        return this.idEnergie;
    }
    public void setIdEnergie(String idEnergie)throws Exception{
        this.idEnergie=idEnergie;
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

    
    public Energie()throws Exception{}
    public Energie(String id, String intitule, int etat)throws Exception{
        this.setIdEnergie(id);
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
            String sql="INSERT INTO Energie VALUES(DEFAULT, '"+this.getIntitule()+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }
    public Energie[] getAll(Connection con)throws Exception{
        Vector<Energie> listEnergie= new Vector<Energie>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM Energie where etat != 10";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                int etat=result.getInt(3);
                Energie m = new Energie(id, intitule, etat);
                listEnergie.add(m);
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
        Energie[] energies= new Energie[listEnergie.size()];
        listEnergie.toArray(energies);
        return energies;
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
            String sql="UPDATE Energie SET etat=10 WHERE id_energie='"+this.getIdEnergie()+"'";
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
            String sql="UPDATE Energie SET intitule='"+this.getIntitule()+"' WHERE id_energie='"+this.getIdEnergie()+"'";
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