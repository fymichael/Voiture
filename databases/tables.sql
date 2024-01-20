CREATE DATABASE vaika_occasion;
\c vaika_occasion;

--Role d'un profil
CREATE TABLE role (
    id_role INTEGER PRIMARY KEY,
    intitule VARCHAR(30)    
);

--Profil
CREATE SEQUENCE seq_profil;
CREATE TABLE profil(
    id_profil INTEGER PRIMARY KEY default nextval('seq_profil'),
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    date_naissance DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    mdp VARCHAR(30) NOT NULL,
    contact VARCHAR(20) NULL,
    id_role INTEGER REFERENCES role(id_role),
    status INTEGER NOT NULL
);

--Categorie d'une voiture
CREATE SEQUENCE seq_categorie;
CREATE TABLE categorie (
    id_categorie INTEGER PRIMARY KEY default nextval('seq_categorie'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

CREATE SEQUENCE seq_commission;
CREATE TABLE commission (
    id_commission INTEGER PRIMARY KEY default nextval('seq_commission'),
    commission DOUBLE precision,
    date_changement DATE
);

--Couleur
CREATE SEQUENCE seq_couleur;
CREATE TABLE couleur (
    id_couleur INTEGER PRIMARY KEY default nextval('seq_couleur'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--energie
CREATE SEQUENCE seq_energie;
CREATE TABLE energie (
    id_energie INTEGER PRIMARY KEY default nextval('seq_energie'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--Marque
CREATE SEQUENCE seq_marque;
CREATE TABLE marque (
    id_marque INTEGER PRIMARY KEY default nextval('seq_marque'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--Modele
CREATE SEQUENCE seq_modele;
CREATE TABLE modele (
    id_modele INTEGER PRIMARY KEY default nextval('seq_modele'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--Mode de tranmssion ou boite de vitesse
CREATE SEQUENCE seq_mode_transmission;
CREATE TABLE mode_transmission (
    id_mode_transmission INTEGER PRIMARY KEY default nextval('seq_mode_transmission'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--Lieu disponible 
CREATE SEQUENCE seq_lieu;
CREATE TABLE lieu (
    id_lieu INTEGER PRIMARY KEY default nextval('seq_lieu'),
    intitule VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL
);

--Voiture
CREATE SEQUENCE seq_voiture;
CREATE TABLE voiture (
    id_voiture INTEGER PRIMARY KEY default nextval('seq_voiture'),
    id_marque INTEGER REFERENCES marque(id_marque),
    id_categorie INTEGER REFERENCES categorie(id_categorie),
    id_modele INTEGER REFERENCES modele(id_modele),
    id_energie INTEGER REFERENCES energie(id_energie),
    id_couleur INTEGER REFERENCES couleur(id_couleur),
    id_mode_transmission INTEGER REFERENCES mode_transmission(id_mode_transmission),
    id_lieu INTEGER REFERENCES lieu(id_lieu),
    annee_sortie VARCHAR(5),
    immatriculation VARCHAR(7),
    autonomie DOUBLE PRECISION,
    modele VARCHAR(20),
    nb_porte INTEGER,
    nb_siege INTEGER,
    kilometrage DOUBLE PRECISION
);

--Photos des voitures
CREATE SEQUENCE seq_voiture_photo;
CREATE TABLE voiture_photo (
    id_voiture INTEGER REFERENCES voiture(id_voiture),
    photo TEXT
);

--Annonce
CREATE SEQUENCE seq_annonce;
CREATE TABLE annonce (
    id_annonce INTEGER PRIMARY KEY default nextval('seq_annonce'),
    id_voiture INTEGER REFERENCES voiture(id_voiture),
    id_profil INTEGER REFERENCES profil(id_profil),
    description VARCHAR(300),
    date_annonce TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    prix DOUBLE PRECISION,
    commission DOUBLE PRECISION DEFAULT 0,
    status INTEGER
);