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

    public void insert(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "INSERT INTO Mode_Transmission VALUES(DEFAULT, '" + this.getIntitule() + "')";
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

    public ModeTransmission getById(Connection con, int idModeTransmission) throws Exception {
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        ModeTransmission ModeTransmission = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Mode_Transmission where etat != 10 and id_Mode_Transmission = " + idModeTransmission;
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                ModeTransmission = new ModeTransmission(id, intitule, etat);
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
        return ModeTransmission;
    }

    public ModeTransmission[] getAll(Connection con) throws Exception {
        Vector<ModeTransmission> listModeTransmission = new Vector<ModeTransmission>();
        boolean valid = true;
        Statement state = null;
        ResultSet result = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            String sql = "SELECT * FROM Mode_Transmission where etat != 10";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String intitule = result.getString(2);
                int etat = result.getInt(3);
                ModeTransmission m = new ModeTransmission(id, intitule, etat);
                listModeTransmission.add(m);
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
        ModeTransmission[] ModeTransmissions = new ModeTransmission[listModeTransmission.size()];
        listModeTransmission.toArray(ModeTransmissions);
        return ModeTransmissions;
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
            String sql = "UPDATE Mode_Transmission SET etat=10 WHERE id_Mode_Transmission='" + this.getIdModeTransmission() + "'";
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

    public void update(Connection con) throws Exception {
        boolean valid = true;
        Statement stmt = null;
        try {
            if (con == null) {
                con = Connect.connectDB();
                valid = false;
            }
            stmt = con.createStatement();
            String sql = "UPDATE Mode_Transmission SET intitule='" + this.getIntitule() + "' WHERE id_Mode_Transmission='"
                    + this.getIdModeTransmission() + "'";
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
