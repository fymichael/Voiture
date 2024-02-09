CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".seq_annonce START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_categorie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_client START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_commission START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_couleur START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_energie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_lieu START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_marque START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_mode_transmission START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_modele START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_vente START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_voiture START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE  TABLE "public".categorie ( 
	id_categorie         varchar(7) DEFAULT ('CTG'::text || lpad((nextval('seq_categorie'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT categorie_pkey PRIMARY KEY ( id_categorie )
 );

CREATE  TABLE "public".commission ( 
	id_commission        varchar(7) DEFAULT ('COM'::text || lpad((nextval('seq_commission'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	valeur               double precision    ,
	date_changement      date    ,
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
	mdp                  text  NOT NULL  ,
	status               integer DEFAULT 1   ,
	contact              varchar    ,
	id_role              integer    ,
	username             varchar(30)    ,
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
	id_vente             varchar(7) DEFAULT ('VNT'::text || lpad((nextval('seq_vente'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_annonce           varchar(7)    ,
	"date"               timestamp DEFAULT CURRENT_TIMESTAMP   ,
	CONSTRAINT vente_id_annonce_fkey FOREIGN KEY ( id_annonce ) REFERENCES "public".annonce( id_annonce )   
 );

CREATE OR REPLACE VIEW v_annonce AS SELECT "public".v_annonce,
    a.id_voiture,
    a.description,
    a.date,
    a.prix,
    a.id_profil,
    a.status,
    v.id_marque,
    m.intitule AS marque,
    v.id_categorie,
    c.intitule AS categorie,
    v.id_energie,
    e.intitule AS energie,
    v.id_couleur,
    coul.intitule AS couleur,
    v.id_mode_transmission,
    t.intitule AS mode_transmission,
    v.id_lieu,
    l.intitule AS lieu,
    v.anne_sortie,
    v.immatriculation,
    v.autonomie,
    v.model,
    v.nb_porte,
    v.nb_siege
   FROM (((((((annonce a
     JOIN voiture v ON (((a.id_voiture)::text = (v.id_voiture)::text)))
     JOIN marque m ON (((v.id_marque)::text = (m.id_marque)::text)))
     JOIN categorie c ON (((v.id_categorie)::text = (c.id_categorie)::text)))
     JOIN energie e ON (((v.id_energie)::text = (e.id_energie)::text)))
     JOIN couleur coul ON (((v.id_couleur)::text = (coul.id_couleur)::text)))
     JOIN mode_transmission t ON (((v.id_mode_transmission)::text = (t.id_mode_transmission)::text)))
     JOIN lieu l ON (((v.id_lieu)::text = (l.id_lieu)::text)));


CREATE OR REPLACE VIEW v_detail_annonce AS SELECT 
       a.id_annonce,
       a.id_voiture,
       a.description,
       a.id_profil,
       a.date AS date_annonce,
       a.prix,
       a.status AS status_annonce,
       v.id_marque,
       m.intitule AS marque,
       v.id_categorie,
       c.intitule AS categorie,
       v.model,
       v.id_energie,
       e.intitule AS energie,
       v.id_couleur,
       coul.intitule AS couleur,
       v.id_mode_transmission,
       t.intitule AS mode_transmission,
       v.id_lieu,
       l.intitule AS lieu,
       v.anne_sortie,
       v.immatriculation,
       v.autonomie,
       v.nb_porte,
       v.nb_siege,
       v.id_specification,
       s.intitule AS specification,
       v.kilometrage
   FROM (((((((annonce a
     JOIN voiture v ON (((a.id_voiture)::text = (v.id_voiture)::text)))
     JOIN marque m ON (((v.id_marque)::text = (m.id_marque)::text)))
     JOIN categorie c ON (((v.id_categorie)::text = (c.id_categorie)::text)))
     JOIN energie e ON (((v.id_energie)::text = (e.id_energie)::text)))
     JOIN couleur coul ON (((v.id_couleur)::text = (coul.id_couleur)::text)))
     JOIN mode_transmission t ON (((v.id_mode_transmission)::text = (t.id_mode_transmission)::text)))
     JOIN lieu l ON (((v.id_lieu)::text = (l.id_lieu)::text)))
     JOIN specification s ON (((v.id_specification)::text = (s.id_specification)::text));


CREATE OR REPLACE VIEW v_commission AS SELECT "public".v_commission,
    a.id_voiture,
    a.date AS date_annonce,
    c.id_commission,
    c.valeur,
    c.date_changement,
    abs(EXTRACT(epoch FROM ((c.date_changement)::timestamp without time zone - a.date))) AS diff_date
   FROM (annonce a
     CROSS JOIN LATERAL ( SELECT c_1.id_commission,
            c_1.valeur,
            c_1.date_changement
           FROM commission c_1
          ORDER BY (abs(EXTRACT(epoch FROM ((c_1.date_changement)::timestamp without time zone - a.date)))), c_1.date_changement
         LIMIT 1) c);

CREATE OR REPLACE VIEW v_detail_voiture AS SELECT "public".v_detail_voiture,
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
   FROM (((((((voiture v
     JOIN marque m ON (((m.id_marque)::text = (v.id_marque)::text)))
     JOIN categorie c ON (((c.id_categorie)::text = (v.id_categorie)::text)))
     JOIN specification mo ON (((mo.id_specification)::text = (v.id_specification)::text)))
     JOIN energie e ON (((e.id_energie)::text = (v.id_energie)::text)))
     JOIN couleur co ON (((co.id_couleur)::text = (v.id_couleur)::text)))
     JOIN mode_transmission md ON (((md.id_mode_transmission)::text = (v.id_mode_transmission)::text)))
     JOIN lieu l ON (((l.id_lieu)::text = (v.id_lieu)::text)))
  WHERE ((m.etat <> 0) AND (c.etat <> 0) AND (mo.etat <> 0) AND (e.etat <> 0) AND (co.etat <> 0));

CREATE OR REPLACE VIEW v_marque AS SELECT "public".v_marque,
    m.intitule,
    count("public".v_marque) AS nombre_ventes
   FROM (((vente v
     JOIN annonce a ON (((v.id_annonce)::text = (a.id_annonce)::text)))
     JOIN voiture c ON (((a.id_voiture)::text = (c.id_voiture)::text)))
     JOIN marque m ON ((("public".v_marque)::text = (c.id_marque)::text)))
  GROUP BY "public".v_marque, m.intitule;

CREATE OR REPLACE VIEW v_marque1 AS SELECT "public".v_marque1,
    marque.intitule,
    0 AS nombre_ventes
   FROM marque
UNION ALL
 SELECT v_"public".v_marque1,
    v_marque.intitule,
    v_marque.nombre_ventes
   FROM v_marque;

CREATE OR REPLACE VIEW "public".v_marque_plus_vendue AS SELECT id_marque,
    intitule,
    max(nombre_ventes) AS nombre_ventes
   FROM v_marque1
  GROUP BY id_marque, intitule
  ORDER BY (max(nombre_ventes)) DESC;

CREATE OR REPLACE VIEW "public".v_nombre_element AS SELECT ( SELECT count(*) AS count
           FROM categorie) AS nb_categorie,
    ( SELECT count(*) AS count
           FROM couleur) AS nb_couleur,
    ( SELECT count(*) AS count
           FROM energie) AS nb_energie,
    ( SELECT count(*) AS count
           FROM marque) AS nb_marque,
    ( SELECT count(*) AS count
           FROM mode_transmission) AS nb_mode_transmission,
    ( SELECT count(*) AS count
           FROM specification) AS nb_specification;

CREATE OR REPLACE VIEW v_profil AS SELECT "public".v_profil,
    p.nom,
    p.prenom,
    p.date_naissance,
    p.email,
    p.mdp,
    p.contact,
    p.id_role,
    r.intitule AS role,
    p.username
   FROM (profil p
     JOIN role r ON ((p.id_role = r.id_role)));

INSERT INTO "public".categorie( id_categorie, intitule, etat ) VALUES ( 'CTG0001', 'Compact', 1);
INSERT INTO "public".categorie( id_categorie, intitule, etat ) VALUES ( 'CTG0002', 'SUV', 1);
INSERT INTO "public".categorie( id_categorie, intitule, etat ) VALUES ( 'CTG0003', 'Sedan', 1);
INSERT INTO "public".commission( id_commission, valeur, date_changement ) VALUES ( 'COM0001', 5.0, '2022-01-01');
INSERT INTO "public".commission( id_commission, valeur, date_changement ) VALUES ( 'COM0002', 7.5, '2022-02-01');
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0001', 'Red', 1);
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0002', 'Blue', 1);
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0003', 'Green', 1);
INSERT INTO "public".energie( id_energie, intitule, etat ) VALUES ( 'ENG0001', 'Essence', 1);
INSERT INTO "public".energie( id_energie, intitule, etat ) VALUES ( 'ENG0002', 'Diesel', 1);
INSERT INTO "public".energie( id_energie, intitule, etat ) VALUES ( 'ENG0003', 'Hybride', 1);
INSERT INTO "public".lieu( id_lieu, intitule, status ) VALUES ( 'LOC0001', 'Paris', 1);
INSERT INTO "public".lieu( id_lieu, intitule, status ) VALUES ( 'LOC0002', 'Marseille', 1);
INSERT INTO "public".lieu( id_lieu, intitule, status ) VALUES ( 'LOC0003', 'Lyon', 1);
INSERT INTO "public".marque( id_marque, intitule, etat ) VALUES ( 'MRQ0001', 'Toyota', 1);
INSERT INTO "public".marque( id_marque, intitule, etat ) VALUES ( 'MRQ0002', 'Ford', 1);
INSERT INTO "public".marque( id_marque, intitule, etat ) VALUES ( 'MRQ0003', 'Honda', 1);
INSERT INTO "public".mode_transmission( id_mode_transmission, intitule, etat ) VALUES ( 'MTR0001', 'Automatic', 1);
INSERT INTO "public".mode_transmission( id_mode_transmission, intitule, etat ) VALUES ( 'MTR0002', 'Manual', 1);
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 1, 'Administrateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 2, 'Utilisateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 3, 'Client');
INSERT INTO "public".specification( id_specification, intitule, etat ) VALUES ( 'SPC0001', 'Basic', 1);
INSERT INTO "public".specification( id_specification, intitule, etat ) VALUES ( 'SPC0002', 'Luxury', 1);
INSERT INTO "public".specification( id_specification, intitule, etat ) VALUES ( 'SPC0003', 'Sports', 1);
INSERT INTO "public".voiture( id_voiture, id_marque, id_categorie, id_specification, id_energie, id_couleur, anne_sortie, immatriculation, autonomie, id_mode_transmission, status, model, nb_porte, nb_siege, kilometrage, id_lieu ) VALUES ( 'CAR0001', 'MRQ0001', 'CTG0001', 'SPC0001', 'ENG0001', 'CLR0001', '2022', 'ABC123', 500.0, 'MTR0001', 1, 'Corolla', 4, 5, 10000.0, 'LOC0001');
INSERT INTO "public".voiture( id_voiture, id_marque, id_categorie, id_specification, id_energie, id_couleur, anne_sortie, immatriculation, autonomie, id_mode_transmission, status, model, nb_porte, nb_siege, kilometrage, id_lieu ) VALUES ( 'CAR0002', 'MRQ0002', 'CTG0002', 'SPC0002', 'ENG0002', 'CLR0002', '2021', 'XYZ456', 600.0, 'MTR0002', 1, 'Explorer', 5, 7, 15000.0, 'LOC0002');
INSERT INTO "public".voiture( id_voiture, id_marque, id_categorie, id_specification, id_energie, id_couleur, anne_sortie, immatriculation, autonomie, id_mode_transmission, status, model, nb_porte, nb_siege, kilometrage, id_lieu ) VALUES ( 'CAR0003', 'MRQ0003', 'CTG0003', 'SPC0003', 'ENG0003', 'CLR0003', '2020', 'DEF789', 400.0, 'MTR0001', 1, 'Civic', 4, 5, 12000.0, 'LOC0003');
INSERT INTO "public".voiture_photo( id_voiture, photo ) VALUES ( 'CAR0001', 'url_photo_1');
INSERT INTO "public".voiture_photo( id_voiture, photo ) VALUES ( 'CAR0002', 'url_photo_2');
INSERT INTO "public".voiture_photo( id_voiture, photo ) VALUES ( 'CAR0003', 'url_photo_3');
INSERT INTO "public".profil( id_profil, nom, prenom, date_naissance, email, mdp, status, contact, id_role, username ) VALUES ( 'PRF0010', 'Arena', 'Arena', '2004-10-10', 'arena@gmail.com', '$2a$10$8sn627H5i3yZVFLVlE4Im.AuFr5xcuvVmZJW5Z1XxtQeiNenNPR7a', 1, '0348756921', 1, 'arena');
INSERT INTO "public".profil( id_profil, nom, prenom, date_naissance, email, mdp, status, contact, id_role, username ) VALUES ( 'PRF0011', 'Angoty', 'Angoty', '2006-08-01', 'angoty@gmail.com', '$2a$10$epxJ0kdIHLmGTXYYPGd.h.B8Pg/aO9U8PDEwfDSVioeL0YjfLCXKO', 1, '0332654789', 1, 'angoty');
INSERT INTO "public".profil( id_profil, nom, prenom, date_naissance, email, mdp, status, contact, id_role, username ) VALUES ( 'PRF0012', 'Fy', 'Michael', '2005-08-01', 'fymichael@gmail.com', '$2a$10$QuTrDKkr5NG/lSDa/xWAAe769E465GWR/02Gka2TZRc3Bf5fuivd2', 1, '0342665394', 3, 'fy');
INSERT INTO "public".annonce( id_annonce, id_voiture, description, "date", prix, id_profil, status ) VALUES ( 'ANO0001', 'CAR0001', 'Good condition', '2022-01-15 12:00:00 PM', 20000.0, 'PRF0012', 1);
INSERT INTO "public".annonce( id_annonce, id_voiture, description, "date", prix, id_profil, status ) VALUES ( 'ANO0002', 'CAR0002', 'Excellent condition', '2022-02-20 03:30:00 PM', 30000.0, 'PRF0012', 1);
INSERT INTO "public".vente( id_vente, id_annonce, "date" ) VALUES ( 'VNT0001', 'ANO0001', '2024-12-31 12:00:00 AM');