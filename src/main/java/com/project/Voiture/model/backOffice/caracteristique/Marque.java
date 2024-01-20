package com.project.Voiture.model.backOffice.caracteristique;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

import com.project.Voiture.model.connection.Connect;

public class Marque {
    String idMarque;
    String intitule;
    int etat;

    public String getIdMarque() {
        return this.idMarque;
    }

    public void setIdMarque(String idMarque) throws Exception {
        this.idMarque = idMarque;
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

    public Marque() throws Exception {
    }

    public Marque(String id, String intitule, int etat) throws Exception {
        this.setIdMarque(id);
        this.setIntitule(intitule);
        this.setEtat(etat);
    }

    public void insert(String intitule, Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            this.setIntitule(intitule);
            String sql = "INSERT INTO Marque VALUES(DEFAULT, '" + this.getIntitule() + "')";
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

    public Marque getById(Connection con, int idMarque) throws Exception {
        Marque marque = null;
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Marque where etat != 0 and id_marque = "+idMarque;
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                marque = new Marque(id, intitule, etat);
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
        return marque;
    }

    public Marque[] getAll(Connection con) throws Exception {
        Vector<Marque> listMarque = new Vector<Marque>();
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Marque where etat != 0 ";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                Marque m = new Marque(id, intitule, etat);
                listMarque.add(m);
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
        Marque[] marques = new Marque[listMarque.size()];
        listMarque.toArray(marques);
        return marques;
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
            String sql = "UPDATE Marque SET intitule='" + this.getIntitule() + "' WHERE id_marque='"
                    + this.getIdMarque() + "'";
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
            String sql = "UPDATE Marque SET etat=0 WHERE id_marque='" + this.getIdMarque() + "'";
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