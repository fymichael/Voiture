CREATE DATABASE voiture;
\c voiture;


CREATE SEQUENCE seq_marque MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_modele MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_energie MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_categorie MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_couleur MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_annonce MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_voiture MINVALUE 1 MAXVALUE 9999 START WITH 1 INCREMENT BY 1;

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

CREATE TABLE voiture(
    id_voiture VARCHAR(7) DEFAULT ('CAR' || LPAD(nextval('seq_voiture')::TEXT, 4, '0')) , 
    id_marque VARCHAR(7),
    id_categorie VARCHAR(7),
    id_modele VARCHAR(7),
    id_energie VARCHAR(7),
    id_couleur VARCHAR(7),
    anne_sortie VARCHAR(7),
    immatriculation VARCHAR(7),
    autonomie DOUBLE PRECISION,
    FOREIGN KEY (id_marque) REFERENCES marque(id_marque),
    FOREIGN KEY (id_categorie) REFERENCES categorie(id_categorie),
    FOREIGN KEY (id_couleur) REFERENCES couleur(id_couleur),
    FOREIGN KEY (id_energie) REFERENCES energie(id_energie),
    FOREIGN KEY (id_modele) REFERENCES modele(id_modele)
);

CREATE TABLE annonce(
    id_annonce VARCHAR(7) DEFAULT ('ANO' || LPAD(nextval('seq_annonce')::TEXT, 4, '0')) PRIMARY KEY,
    id_voiture VARCHAR(7),
    descriprion VARCHAR(100),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    prix DOUBLE PRECISION,
    id_client VARCHAR(7),
    FOREIGN KEY (id_voiture) REFERENCES Voiture(id_voiture),
    FOREIGN KEY (id_client) REFERENCES client(id_client)
);

CREATE TABLE voiture_photo(
    id_voiture VARCHAR(7),
    photo TEXT,
    FOREIGN KEY (id_voiture) REFERENCES Voiture(id_voiture)
);