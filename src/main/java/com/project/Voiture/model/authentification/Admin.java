package com.project.Voiture.model.authentification;
import java.sql.Connection;
import java.sql.Statement;

import com.project.Voiture.model.connection.Connect;

import java.sql.ResultSet;

public class Admin{
    int id;
    String nom;
    String prenom;
    String email;
    String mdp;


    public int getId(){
        return this.id;
    }
    public void setId(int idAdmin)throws Exception{
        if(idAdmin<0){
            throw new Exception("idAdmin invalide");
        }
        this.id=idAdmin;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom)throws Exception{
        if(nom.length()==0 || nom==null || nom.equals("")){
            throw new Exception("nom de l administrateur invalide");
        }
        this.nom=nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom)throws Exception{
        if(prenom.length()==0 || prenom==null || prenom.equals("")){
            throw new Exception("prenom de l administrateur invalide");
        }
        this.prenom=prenom;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email)throws Exception{
        if(email.length()==0 || email==null || email.equals("")){
            throw new Exception("email invalide");
        }
        this.email=email;
    }
    public String getMdp(){
        return this.mdp;
    }
    public void setMdp(String mdp)throws Exception{
        if(mdp.length()==0 || mdp==null || mdp.equals("")){
            throw new Exception("mot de passe invalide");
        }
        this.mdp=mdp;
    }

    public Admin()throws Exception{}
    public Admin(int id, String nom, String prenom,  String email, String mdp)throws Exception{
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setMdp(mdp);
    }

    public Admin getUtilisateur(Connection con)throws Exception{
        Admin a = new Admin();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectDB();
                valid=false;
            }
            String sql = "SELECT * FROM profil WHERE email='"+this.getEmail()+"' AND mdp='"+this.getMdp()+"' AND id_role=1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                int id= result.getInt(1);
                String nom= result.getString(2);
                String prenom=result.getString(3);
                String email= result.getString(4);
                String mdp= result.getString(5);
                a = new Admin(id, nom, prenom, email, mdp);
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
        return a;
    }

    
}