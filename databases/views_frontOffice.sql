--Vue d'une annonce
CREATE OR REPLACE VIEW v_annonce AS
SELECT a.id_annonce, a.id_voiture, a.description, a.id_profil, a.date_annonce,a.prix, a.commission,
a.status, v.id_marque, m.intitule marque, v.id_categorie, c.intitule categorie, mod.id_modele, 
mod.intitule modele, v.id_energie, e.intitule energie, v.id_couleur, coul.intitule couleur, 
v.id_mode_transmission, t.intitule mode_transmission, v.id_lieu, 
l.intitule lieu, v.annee_sortie, v.immatriculation, v.autonomie, v.modele modele_plus, v.nb_porte, v.nb_siege
FROM annonce a 
JOIN voiture v ON a.id_voiture = v.id_voiture
JOIN marque m ON v.id_marque = m.id_marque
JOIN categorie c ON v.id_categorie = c.id_categorie
JOIN energie e ON v.id_energie = e.id_energie
JOIN couleur coul ON v.id_couleur = coul.id_couleur
JOIN mode_transmission t ON v.id_mode_transmission = t.id_mode_transmission
JOIN lieu l ON v.id_lieu = l.id_lieu
JOIN modele mod ON v.id_modele = mod.id_modele;

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
--Vue d'un profil et role
CREATE OR REPLACE VIEW v_profil AS
SELECT p.id_profil, p.nom, p.prenom, p.date_naissance, p.email, p.mdp, p.contact, p.id_role, 
r.intitule role FROM profil p JOIN role r ON p.id_role = r.id_role;