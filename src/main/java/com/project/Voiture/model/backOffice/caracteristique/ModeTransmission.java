package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class ModeTransmission {

    String idModeTransmission;
    String intitule;
    int etat;

    public String getIdModeTransmission() {
        return this.idModeTransmission;
    }

    public void setIdModeTransmission(String idModeTransmission) throws Exception {
        this.idModeTransmission = idModeTransmission;
    }

    public String getIntitule() {
        return this.intitule;
    }

    public void setIntitule(String intitule) throws Exception {
        if (intitule.length() == 0) {
            throw new Exception("intitule nulle");
        }
        this.intitule = intitule;
    }

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) throws Exception {
        if (etat < 0) {
            throw new Exception("etat invalide: negatif");
        }
        this.etat = etat;
    }

    public void setEtat(String etat) throws Exception {
        int a = Integer.valueOf(etat);
        if (etat.length() == 0) {
            throw new Exception("etat invalide: null");
        }
        this.setEtat(a);
    }

    public ModeTransmission() throws Exception {
        }

    public ModeTransmission(String id, String intitule, int etat) throws Exception {
            this.setIdModeTransmission(id);
            this.setIntitule(intitule);
            this.setEtat(etat);
        }

        public ModeTransmission getById(Connection con)throws Exception{
            ModeTransmission marque= null;
            boolean valid=true;
            Statement state=null;
            ResultSet result=null;
            try {
                if(con==null){
                    con=Connect.connectDB();
                    valid=false;
                }
                String sql = "SELECT * FROM mode_transmission WHERE id_mode_transmission='"+this.getIdModeTransmission()+"'";
                state = con.createStatement();
                System.out.println(sql);
                result = state.executeQuery(sql);
                while(result.next()){
                    String id= result.getString(1);
                    String intitule= result.getString(2);
                    int etat=result.getInt(3);
                    marque= new ModeTransmission(id, intitule, etat);
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
        public ModeTransmission[] getAll(Connection con)throws Exception{
            Vector<ModeTransmission> listModeTransmission= new Vector<ModeTransmission>();
            boolean valid=true;
            Statement state=null;
            ResultSet result=null;
            try {
                if(con==null){
                    con=Connect.connectDB();
                    valid=false;
                }
                String sql = "SELECT * FROM mode_transmission WHERE etat=1";
                state = con.createStatement();
                result = state.executeQuery(sql);
                while(result.next()){
                    String id= result.getString(1);
                    String intitule= result.getString(2);
                    int etat=result.getInt(3);
                    ModeTransmission m = new ModeTransmission(id, intitule, etat);
                    listModeTransmission.add(m);
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
            ModeTransmission[] mts= new ModeTransmission[listModeTransmission.size()];
            listModeTransmission.toArray(mts);
            return mts;
        }
        public ModeTransmission insert(Connection con)throws Exception{
            boolean valid=true;
            Statement stmt =null;
            ResultSet res=null;
            try{
                if(con==null){
                    con = Connect.connectDB();
                    valid=false;
                } 
                stmt= con.createStatement();
                String sql="INSERT INTO mode_transmission VALUES(DEFAULT, '"+this.getIntitule()+"', 1) returning id_mode_transmission";
                System.out.println(sql);
                res=stmt.executeQuery(sql);
                if(res.next()) this.setIdModeTransmission(res.getString("id_mode_transmission"));
                System.out.println(this.getIdModeTransmission());
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
                String sql="UPDATE mode_transmission SET intitule='"+this.getIntitule()+"' WHERE id_mode_transmission='"+this.getIdModeTransmission()+"'";
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
                String sql="UPDATE mode_transmission SET etat=10 WHERE id_mode_transmission='"+this.getIdModeTransmission()+"'";
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
