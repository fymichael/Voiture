--Role
INSERT INTO role (id_role, intitule)
VALUES
    (1, 'Administrateur'),
    (2, 'Utilisateur'),
    (3, 'Client');

--Utilisateurs
INSERT INTO profil (nom, prenom, date_naissance, email, mdp, contact, id_role, status)
VALUES
    ('INSSA', 'Chalman', '19-06-2002', 'chalmanInssa1962002@gmail.com', 'chalman', '0345091434', 3, 1),
    ('Fy', 'Michael', '10-08-2005', 'fyMichael@gmail.com', 'fy', '0341111111', 3, 1),
    ('Arena', 'Gracia', '10-02-2003', 'arenaGracia@gmail.com', 'arena', '0340000000', 1, 1),
    ('Rabarijaona', 'Angoty', '27-11-2003', 'angotyRabarijaona@gmail.com', 'angoty', 0342222222, 1, 1),
    ('Bertrand', 'Luc', '18-07-1988', 'luc.bertrand@email.com', 'pass654', '111222333', 2, 1),
    ('Moreau', 'Emma', '05-01-1993', 'emma.moreau@email.com', 'secret456', '999000111', 3, 1);

--Categories
INSERT INTO categorie (intitule, status)
VALUES
    ('4x4, suv & crossover', 1),
    ('Citadine', 1),
    ('Berline', 1),
    ('Break', 1),
    ('Cabriolet', 1),
    ('Coupe', 1),
    ('Monospace', 1),
    ('Bus et minibus', 1),
    ('Fourgon', 1),
    ('Camion', 1),
    ('sports', 1);

--Couleurs
INSERT INTO couleur (intitule, status)
VALUES
    ('Rouge', 1),
    ('Bleue', 1),
    ('Vert', 1),
    ('Noir', 1),
    ('Blanc', 1),
    ('Silver', 1),
    ('Jaune', 1),
    ('Gris', 1),
    ('Orange', 1),
    ('Marron', 1);

--Energie
INSERT INTO energie (intitule, status)
VALUES
    ('Diesel', 1),
    ('Essence', 1),
    ('Electrique', 1);

--Marque
INSERT INTO marque (intitule, status)
VALUES
    ('Toyota', 1),
    ('Ford', 1),
    ('Renault', 1),
    ('Chevrolet', 1),
    ('Volkswagen', 1),
    ('Nissan', 1),
    ('BMW', 1),
    ('Mercedes-Benz', 1),
    ('Audi', 1),
    ('Tesla', 1);

--Modele
INSERT INTO modele (intitule, status)
VALUES
    ('208', 1),
    ('308', 1),
    ('26', 1),
    ('16', 1),
    ('200', 1),
    ('206', 1);

--Mode transmission
INSERT INTO mode_transmission (intitule, status)
VALUES
    ('Automatique', 1),
    ('Mannuelle', 1);

--Lieu
INSERT INTO lieu (intitule, status)
VALUES
    ('Antananarivo', 1),
    ('Toamasina', 1),
    ('Mahajanga', 1),
    ('Antsirabe', 1),
    ('Diego', 1),
    ('Fianarantsoa', 1),
    ('Toliara', 1);

--Voiture
INSERT INTO voiture (id_marque, id_categorie, id_modele, id_energie, id_couleur, 
id_mode_transmission, id_lieu, annee_sortie, immatriculation, autonomie, modele, nb_porte, nb_siege, 
kilometrage)
VALUES 
    (1, 1, 1, 2, 2, 2, 1, '2000', 'TB-0023', 45, 'Toyota 208', 4, 5, 1000),
    (7, 3, 6, 1, 4, 1, 1, '2010', 'TB-0355', 40, 'Nissan 206', 5, 5, 36),
    (9, 5, 1, 2, 5, 1, 2, '2016', 'TB-0232', 53, 'Audi 206', 5, 5, 10);

--Annonce
INSERT INTO annonce (id_voiture, id_profil,description, prix, commission, status) 
VALUES
    (1, 1,'Toyota 208 ANNEE 2000 ESSENCE OCCASION DE LA REUNION EN EXCELLENTE ETAT', 10500300, 20, 1),
    (2, 2,'Nissan 206 ANNEE 2010 ESSENCE OCCASION DE LA REUNION EN EXCELLENTE ETAT', 9500000, 15, 1),
    (3, 6,'Audi 206 ANNEE 2016 ESSENCE OCCASION DE LA REUNION EN EXCELLENTE ETAT', 13400500, 18, 1);