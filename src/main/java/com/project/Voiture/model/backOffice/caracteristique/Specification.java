package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Specification {
    String idSpecification;
    String intitule;
    int etat;

    public String getIdSpecification() {
        return this.idSpecification;
    }

    public void setIdSpecification(String idModele) throws Exception {
        this.idSpecification = idModele;
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

    public Specification() throws Exception {
    }

    public Specification(String id, String intitule, int etat) throws Exception {
        this.setIdSpecification(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
    }

    public void insert(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "INSERT INTO specification VALUES(DEFAULT, '" + this.getIntitule() + ",1)";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

    public Specification getById(Connection con, int idModele) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        Specification modele = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM specification where etat != 0 and id_specification = "+idModele;
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                modele = new Specification(id, intitule, etat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (state != null) {
                    state.close();
                }
                if (result != null) {
                    result.close();
                }
                if (valid == false || con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return modele;
    }

    public Specification[] getAll(Connection con) throws Exception {
        Vector<Specification> listModele = new Vector<Specification>();
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM specification where etat != 0";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                Specification m = new Specification(id, intitule, etat);
                listModele.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (state != null) {
                    state.close();
                }
                if (result != null) {
                    result.close();
                }
                if (valid == false || con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Specification[] modeles = new Specification[listModele.size()];
        listModele.toArray(modeles);
        return modeles;
    }

    public void update(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "UPDATE specification SET intitule='" + this.getIntitule() + "' WHERE id_specification='"
                    + this.getIdSpecification() + "'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

    public void delete(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "UPDATE specification SET etat=0 WHERE id_specification='" + this.getIdSpecification() + "'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (!valid) {
                con.close();
            }
        }
    }

}