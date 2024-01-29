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
	CONSTRAINT annonce_pkey PRIMARY KEY ( id_annonce )
 );

CREATE OR REPLACE VIEW v_annonce_client AS SELECT a.id_annonce,
    a.id_voiture,
    a.description,
    a.date,
    a.prix,
    a.id_profil,
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
    co.intitule AS couleur
   FROM (((((voiture v
     JOIN marque m ON (((m.id_marque)::text = (v.id_marque)::text)))
     JOIN categorie c ON (((c.id_categorie)::text = (v.id_categorie)::text)))
     JOIN specification mo ON (((mo.id_specification)::text = (v.id_specification)::text)))
     JOIN energie e ON (((e.id_energie)::text = (v.id_energie)::text)))
     JOIN couleur co ON (((co.id_couleur)::text = (v.id_couleur)::text)))
  WHERE ((m.etat <> 0) AND (c.etat <> 0) AND (mo.etat <> 0) AND (e.etat <> 0) AND (co.etat <> 0));

CREATE OR REPLACE VIEW v_detail_annonce AS SELECT vdv.id_voiture,
    vdv.id_categorie,
    vdv.id_marque,
    vdv.id_specification,
    vdv.id_energie,
    vdv.id_couleur,
    vdv.id_lieu,
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
    vac.id_annonce,
    vac.description,
    vac.date AS date_annonce,
    vac.prix,
    vac.id_profil,
    vac.nom,
    vac.prenom,
    vac.date_naissance,
    vac.email,
    vac.mdp,
    vac.contact
   FROM (v_detail_voiture vdv
     JOIN v_annonce_client vac ON (((vac.id_voiture)::text = (vdv.id_voiture)::text)))

CREATE OR REPLACE VIEW v_profil AS SELECT p.id_profil,
    p.nom,
    p.prenom,
    p.date_naissance,
    p.email,
    p.mdp,
    p.contact,
    p.id_role,
    p.username,
    r.intitule AS role
   FROM (profil p
     JOIN role r ON ((p.id_role = r.id_role)))
  WHERE (p.status <> 0)

INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 1, 'Administrateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 2, 'Utilisateur');
INSERT INTO "public"."role"( id_role, intitule ) VALUES ( 3, 'Client');