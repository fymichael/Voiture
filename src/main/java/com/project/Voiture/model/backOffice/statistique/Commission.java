package com.project.Voiture.model.backOffice.statistique;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Commission {
    String idCommission;
    double valeur;
    Date date;

    public String getIdCommission(){
        return this.idCommission;
    }
    public double getValeur(){
        return this.valeur;
    }
    public Date getDate(){
        return this.date;
    }
    public void setIdCommission(String id)throws Exception{
        if(id==null || id.length()==0){
            throw new Exception("id commission nulle");
        }
        this.idCommission=id;
    }
    public void setValeur(String values)throws Exception{
        double a = Double.valueOf(valeur);
        if(values==null || values.length()==0 || values.equals("")){
            throw new Exception("valeur de commission invalide: null");
        }
        this.setValeur(a);
    }
    public void setValeur(double valeur)throws Exception{
        if(valeur<0){
            throw new Exception("valeur de commission invalide: negative");
        }
        this.valeur=valeur;
    }
    public void setDate(Date date)throws Exception{
        if(date==null){
            throw new Exception("date commission invalide: null");
        }
       this.date=date;
    }
    public Commission ()throws Exception{}
    public Commission (String id, String valeur, Date date)throws Exception{
        this.setIdCommission(id);
        this.setValeur(valeur);
        this.setDate(date);
    }
    public Commission (String id, double valeur, Date date)throws Exception{
        this.setIdCommission(id);
        this.setValeur(valeur);
        this.setDate(date);
    }

    public Commission insert(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        ResultSet res=null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "INSERT INTO commission VALUES(DEFAULT, " + this.getValeur() +", '"+this.getDate()+"')";
            System.out.println(sql);
            int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (affectedRows > 0) {
                res = stmt.getGeneratedKeys();
                if (res.next()) {
                    this.setIdCommission(res.getString(1));
                }
            } 
        }
        catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {stmt.close();            }
            if (!valid) {con.close();            }
        }
        return this;
    }

    public Commission[] getAll(Connection con)throws Exception{
        Vector<Commission> listCommission= new Vector<Commission>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM commission";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                double valeur= result.getDouble(2);
                Date date=result.getDate(3);
                Commission m = new Commission(id, valeur , date);
                listCommission.add(m);
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
        Commission[] marques= new Commission[listCommission.size()];
        listCommission.toArray(marques);
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
            String sql="UPDATE commission SET valeur='"+this.getValeur()+"' WHERE id_commission='"+this.getIdCommission()+"'";
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
            String sql="DELETE FROM commission WHERE id_commission='"+this.getIdCommission()+"'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null){ stmt.close(); }
            if(!valid){ con.close(); }
        }
    }
    
    public Commission getCommissionAnnonce(String id, Connection con)throws Exception{
        Commission commission= null;
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM v_commission WHERE id_annonce='"+id+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                String idC= result.getString("id_commission");
                double valeur= result.getDouble("valeur");
                Date date=result.getDate("date_commission");
                commission= new Commission(idC, valeur, date);
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
        return commission;
    }

    public Commission getCommission(String id, Connection con)throws Exception{
        Commission commission= null;
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM commission WHERE id_commission='"+id+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                String idC= result.getString("id_commission");
                double valeur= result.getDouble("valeur");
                Date date=result.getDate("date_changement");
                commission= new Commission(idC, valeur, date);
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
        return commission;
    }
}
