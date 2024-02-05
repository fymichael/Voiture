CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".seq_annonce START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_categorie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_client START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_couleur START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_commission START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_energie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_lieu START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_marque START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_mode_transmission START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_modele START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_voiture START WITH 1 INCREMENT BY 1 MAXVALUE 9999;


CREATE SEQUENCE "public".seq_vente START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE  TABLE "public".categorie ( 
	id_categorie         varchar(7) DEFAULT ('CTG'::text || lpad((nextval('seq_categorie'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT categorie_pkey PRIMARY KEY ( id_categorie )
 );

CREATE  TABLE "public".commission ( 
	id_commission         varchar(7) DEFAULT ('COM'::text || lpad((nextval('seq_commission'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	valeur               double precision    ,
	date_changement      date  ,
	CONSTRAINT commission_pkey PRIMARY KEY ( id_commission )  
 );

CREATE  TABLE "public".couleur ( 
	id_couleur           varchar(7) DEFAULT ('CLR'::text || lpad((nextval('seq_couleur'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT couleur_pkey PRIMARY KEY ( id_couleur )
 );

CREATE  TABLE "public".energie ( 
	id_energie           varchar(7) DEFAULT ('ENG'::text || lpad((nextval('seq_energie'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT energie_pkey PRIMARY KEY ( id_energie )
 );

CREATE  TABLE "public".lieu ( 
	id_lieu              varchar(7) DEFAULT ('LOC'::text || lpad((nextval('seq_lieu'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)  NOT NULL  ,
	status               integer  NOT NULL  ,
	CONSTRAINT lieu_pkey PRIMARY KEY ( id_lieu )
 );

CREATE  TABLE "public".marque ( 
	id_marque            varchar(7) DEFAULT ('MRQ'::text || lpad((nextval('seq_marque'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT marque_pkey PRIMARY KEY ( id_marque )
 );

CREATE  TABLE "public".mode_transmission ( 
	id_mode_transmission varchar(7) DEFAULT ('MTR'::text || lpad((nextval('seq_mode_transmission'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer    ,
	CONSTRAINT pk_mode_transmission PRIMARY KEY ( id_mode_transmission )
 );

CREATE  TABLE "public"."role" ( 
	id_role              integer  NOT NULL  ,
	intitule             varchar(30)    ,
	CONSTRAINT role_pkey PRIMARY KEY ( id_role )
 );

CREATE  TABLE "public".specification ( 
	id_specification     varchar(7) DEFAULT ('SPC'::text || lpad((nextval('seq_modele'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT modele_pkey PRIMARY KEY ( id_specification )
 );

CREATE  TABLE "public".voiture ( 
	id_voiture           varchar(7) DEFAULT ('CAR'::text || lpad((nextval('seq_voiture'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_marque            varchar(7)    ,
	id_categorie         varchar(7)    ,
	id_specification     varchar(7)    ,
	id_energie           varchar(7)    ,
	id_couleur           varchar(7)    ,
	anne_sortie          varchar(7)    ,
	immatriculation      varchar(7)    ,
	autonomie            double precision    ,
	id_mode_transmission varchar(7)    ,
	status               integer DEFAULT 1   ,
	model                text    ,
	nb_porte             integer    ,
	nb_siege             integer    ,
	kilometrage          double precision    ,
	id_lieu              varchar(7)    ,
	CONSTRAINT voiture_pkey PRIMARY KEY ( id_voiture ),
	CONSTRAINT fk_voiture_marque FOREIGN KEY ( id_marque ) REFERENCES "public".marque( id_marque )   ,
	CONSTRAINT fk_voiture_categorie FOREIGN KEY ( id_categorie ) REFERENCES "public".categorie( id_categorie )   ,
	CONSTRAINT fk_voiture_modele FOREIGN KEY ( id_specification ) REFERENCES "public".specification( id_specification )   ,
	CONSTRAINT fk_voiture_energie FOREIGN KEY ( id_energie ) REFERENCES "public".energie( id_energie )   ,
	CONSTRAINT fk_voiture_couleur FOREIGN KEY ( id_couleur ) REFERENCES "public".couleur( id_couleur )   ,
	CONSTRAINT fk_voiture_mode_transmission FOREIGN KEY ( id_mode_transmission ) REFERENCES "public".mode_transmission( id_mode_transmission )   ,
	CONSTRAINT fk_voiture_lieu FOREIGN KEY ( id_lieu ) REFERENCES "public".lieu( id_lieu )   
 );

CREATE  TABLE "public".voiture_photo ( 
	id_voiture           varchar(7)    ,
	photo                text    ,
	CONSTRAINT fk_voiture_photo_voiture FOREIGN KEY ( id_voiture ) REFERENCES "public".voiture( id_voiture )   
 );

CREATE  TABLE "public".profil ( 
	id_profil            varchar(7) DEFAULT ('PRF'::text || lpad((nextval('seq_client'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	nom                  varchar(30)  NOT NULL  ,
	prenom               varchar(30)  NOT NULL  ,
	date_naissance       date  NOT NULL  ,
	email                varchar(30)  NOT NULL  ,
	mdp                  varchar(30)  NOT NULL  ,
	status               integer DEFAULT 1   ,
	contact              varchar    ,
	id_role              integer    ,
	username			 varchar(30),
	CONSTRAINT client_pkey PRIMARY KEY ( id_profil ),
	CONSTRAINT fk_client_role FOREIGN KEY ( id_role ) REFERENCES "public"."role"( id_role )   
 );
 
CREATE  TABLE "public".annonce ( 
	id_annonce           varchar(7) DEFAULT ('ANO'::text || lpad((nextval('seq_annonce'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_voiture           varchar(7)    ,
	description          varchar(100)    ,
	"date"               timestamp DEFAULT CURRENT_TIMESTAMP   ,
	prix                 double precision    ,
	id_profil            varchar(7)    ,
	status               integer    ,
	CONSTRAINT annonce_pkey PRIMARY KEY ( id_annonce ),
	CONSTRAINT fk_annonce_client FOREIGN KEY ( id_profil ) REFERENCES "public".profil( id_profil )   
 );

 CREATE  TABLE "public".vente ( 
	id_vente           varchar(7) DEFAULT ('VNT'::text || lpad((nextval('seq_vente'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_annonce           varchar(7)    ,
	"date"               timestamp DEFAULT CURRENT_TIMESTAMP   ,
	FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce)
 );

CREATE OR REPLACE VIEW v_annonce_client AS SELECT a.id_annonce,
    a.id_voiture,
    a.description,
    a.date,
    a.prix,
    a.id_profil,
    a.status AS status_annonce,
    c.nom,
    c.prenom,
    c.date_naissance,
    c.email,
    c.mdp,
    c.contact
   FROM (annonce a
     JOIN profil c ON (((c.id_profil)::text = (a.id_profil)::text)))
  WHERE ((a.status <> 0) AND (c.status <> 0));

CREATE OR REPLACE VIEW v_detail_voiture AS SELECT v.id_voiture,
    v.id_categorie,
    v.id_marque,
    v.id_specification,
    v.id_energie,
    v.id_couleur,
	v.id_mode_transmission,
    v.id_lieu,
    v.anne_sortie,
    v.immatriculation,
    v.autonomie,
    v.model,
    v.nb_porte,
    v.nb_siege,
    v.kilometrage,
    m.intitule AS marque,
    c.intitule AS categorie,
    mo.intitule AS specification,
    e.intitule AS energie,
    co.intitule AS couleur,
	md.intitule AS mode_transmission,
	l.intitule AS lieu
   FROM (((((voiture v
     JOIN marque m ON (((m.id_marque)::text = (v.id_marque)::text)))
     JOIN categorie c ON (((c.id_categorie)::text = (v.id_categorie)::text)))
     JOIN specification mo ON (((mo.id_specification)::text = (v.id_specification)::text)))
     JOIN energie e ON (((e.id_energie)::text = (v.id_energie)::text)))
     JOIN couleur co ON (((co.id_couleur)::text = (v.id_couleur)::text)))
	 JOIN mode_transmission md ON (((md.id_mode_transmission)::text = (v.id_mode_transmission)::text))
	 JOIN lieu l ON (((l.id_lieu)::text = (v.id_lieu)::text))
  WHERE ((m.etat <> 0) AND (c.etat <> 0) AND (mo.etat <> 0) AND (e.etat <> 0) AND (co.etat <> 0));

CREATE OR REPLACE VIEW v_detail_annonce AS SELECT vdv.id_voiture,
    vdv.id_categorie,
    vdv.id_marque,
    vdv.id_specification,
    vdv.id_energie,
    vdv.id_couleur,
    vdv.id_lieu,
	vdv.id_mode_transmission,
    vdv.anne_sortie,
    vdv.immatriculation,
    vdv.autonomie,
    vdv.model,
    vdv.nb_porte,
    vdv.nb_siege,
    vdv.kilometrage,
    vdv.marque,
    vdv.categorie,
    vdv.specification,
    vdv.energie,
    vdv.couleur,
	vdv.lieu,
	vdv.mode_transmission,
    vac.id_annonce,
    vac.description,
    vac.date AS date_annonce,
    vac.prix,
    vac.id_profil,
    vac.status_annonce,
    vac.nom,
    vac.prenom,
    vac.date_naissance,
    vac.email,
    vac.mdp,
    vac.contact
   FROM (v_detail_voiture vdv
     JOIN v_annonce_client vac ON (((vac.id_voiture)::text = ( vdv.id_voiture)::text)));

--Vue d'un profil et role
CREATE OR REPLACE VIEW v_profil AS
SELECT p.id_profil, p.nom, p.prenom, p.date_naissance, p.email, p.mdp, p.contact, p.id_role, 
r.intitule role, p.username FROM profil p JOIN role r ON p.id_role = r.id_role;

---Les donnees de tests
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 1, 'Administrateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 2, 'Utilisateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 3, 'Client');

--Categorie
INSERT INTO public.categorie (id_categorie, intitule, etat) VALUES
('CTG0001', 'Compact', 1),
('CTG0002', 'SUV', 1),
('CTG0003', 'Sedan', 1);

--Commission
INSERT INTO public.commission (valeur, date_changement) VALUES
(5.0, '2022-01-01'),
(7.5, '2022-02-01');

--Couleur
INSERT INTO public.couleur (id_couleur, intitule, etat) VALUES
('CLR0001', 'Red', 1),
('CLR0002', 'Blue', 1),
('CLR0003', 'Green', 1);

--Energie
INSERT INTO public.energie (id_energie, intitule, etat) VALUES
('ENG0001', 'Essence', 1),
('ENG0002', 'Diesel', 1),
('ENG0003', 'Hybride', 1);

--Lieu
INSERT INTO public.lieu (id_lieu, intitule, status) VALUES
('LOC0001', 'Paris', 1),
('LOC0002', 'Marseille', 1),
('LOC0003', 'Lyon', 1);

--Marque
INSERT INTO public.marque (id_marque, intitule, etat) VALUES
('MRQ0001', 'Toyota', 1),
('MRQ0002', 'Ford', 1),
('MRQ0003', 'Honda', 1);

--Transmission
INSERT INTO public.mode_transmission (id_mode_transmission, intitule, etat) VALUES
('MTR0001', 'Automatic', 1),
('MTR0002', 'Manual', 1);

--Specification
INSERT INTO public.specification (id_specification, intitule, etat) VALUES
('SPC0001', 'Basic', 1),
('SPC0002', 'Luxury', 1),
('SPC0003', 'Sports', 1);

--Voiture
INSERT INTO public.voiture (id_voiture, id_marque, id_categorie, id_specification, id_energie, id_couleur, 
anne_sortie, immatriculation, autonomie, id_mode_transmission, status, model, nb_porte, nb_siege, kilometrage, id_lieu)
VALUES
('CAR0001', 'MRQ0001', 'CTG0001', 'SPC0001', 'ENG0001', 'CLR0001', '2022', 'ABC123', 500.0, 'MTR0001', 1, 'Corolla', 4, 5, 10000.0, 'LOC0001'),
('CAR0002', 'MRQ0002', 'CTG0002', 'SPC0002', 'ENG0002', 'CLR0002', '2021', 'XYZ456', 600.0, 'MTR0002', 1, 'Explorer', 5, 7, 15000.0, 'LOC0002'),
('CAR0003', 'MRQ0003', 'CTG0003', 'SPC0003', 'ENG0003', 'CLR0003', '2020', 'DEF789', 400.0, 'MTR0001', 1, 'Civic', 4, 5, 12000.0, 'LOC0003');

--Voiture photo
INSERT INTO public.voiture_photo (id_voiture, photo) VALUES
('CAR0001', 'url_photo_1'),
('CAR0002', 'url_photo_2'),
('CAR0003', 'url_photo_3');

INSERT INTO profil (nom, prenom, date_naissance, email, mdp, contact, id_role, status, username)
VALUES
    ('INSSA', 'Chalman', '2002-06-19', 'chalmanInssa1962002@gmail.com', 'chalman', '0345091434', 3, 1, 'chalman'),
    ('Fy', 'Michael', '2005-08-10', 'fyMichael@gmail.com', 'fy', '0341111111', 3, 1, 'fy'),
    ('Arena', 'Gracia', '2003-10-02', 'arenaGracia@gmail.com', 'arena', '0340000000', 1, 1, 'arena'),
    ('Rabarijaona', 'Angoty', '2003-02-18', 'angotyRabarijaona@gmail.com', 'angoty', '0342222222', 1, 1, 'angoty'),
    ('Bertrand', 'Luc', '1988-18-07', 'luc.bertrand@email.com', 'pass654', '111222333', 2, 1, 'bertand'),
    ('Moreau', 'Emma', '1993-05-01-', 'emma.moreau@email.com', 'secret456', '999000111', 3, 1, 'moreau');

--Annonce
INSERT INTO public.annonce (id_annonce, id_voiture, description, "date", prix, id_profil, status)
VALUES
('ANO0001', 'CAR0001', 'Good condition', '2022-01-15 12:00:00', 20000.0, 'PRF0012', 1),
('ANO0002', 'CAR0002', 'Excellent condition', '2022-02-20 15:30:00', 30000.0, 'PRF0012', 1);
