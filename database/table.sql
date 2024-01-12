CREATE DATABASE voiture;
\c voiture;


CREATE SEQUENCE seq_marque MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_modele MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_energie MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_categorie MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_couleur MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;

CREATE TABLE Marque(
    id_marque VARCHAR(7) DEFAULT ('MRQ' || LPAD(nextval('seq_marque')::TEXT, 4, '0')) PRIMARY KEY ,
    intitule VARCHAR(30),
    etat INTEGER DEFAULT 1
);

CREATE TABLE Modele(
    id_modele VARCHAR(7) DEFAULT ('MDL' || LPAD(nextval('seq_modele')::TEXT, 4, '0')) PRIMARY KEY ,
    intitule VARCHAR(30),
    vin_num INTEGER UNIQUE,
    etat INTEGER DEFAULT 1
);

CREATE TABLE Energie(
    id_energie VARCHAR(7) DEFAULT ('ENG' || LPAD(nextval('seq_energie')::TEXT, 4, '0')) PRIMARY KEY ,
    intitule VARCHAR(30),
    etat INTEGER DEFAULT 1
);

CREATE TABLE Categorie(
    id_categorie VARCHAR(7) DEFAULT ('CTG' || LPAD(nextval('seq_categorie')::TEXT, 4, '0')) PRIMARY KEY ,
    intitule VARCHAR(30),
    etat INTEGER DEFAULT 1
);

CREATE TABLE Couleur(
    id_couleur VARCHAR(7) DEFAULT ('CLR' || LPAD(nextval('seq_couleur')::TEXT, 4, '0')) PRIMARY KEY ,
    intitule VARCHAR(30),
    etat INTEGER DEFAULT 1
);

CREATE TABLE Admin(
    id_admin SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    prenom VARCHAR(30),
    email VARCHAR(30),
    mdp VARCHAR(30)
);