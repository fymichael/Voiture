CREATE DATABASE voiture;
\c voiture;


CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".admin_id_admin_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".commission_id_commission_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".seq_annonce START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_categorie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_client START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_couleur START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_energie START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_marque START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_modele START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE SEQUENCE "public".seq_voiture START WITH 1 INCREMENT BY 1 MAXVALUE 9999;

CREATE  TABLE "public"."admin" ( 
	id_admin             serial DEFAULT nextval('admin_id_admin_seq'::regclass) NOT NULL  ,
	nom                  varchar(30)    ,
	prenom               varchar(30)    ,
	email                varchar(30)    ,
	mdp                  varchar(30)    ,
	CONSTRAINT admin_pkey PRIMARY KEY ( id_admin )
 );

CREATE  TABLE "public".categorie ( 
	id_categorie         varchar(7) DEFAULT ('CTG'::text || lpad((nextval('seq_categorie'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT categorie_pkey PRIMARY KEY ( id_categorie )
 );

CREATE  TABLE "public".client ( 
	id_client            varchar(7) DEFAULT ('CLT'::text || lpad((nextval('seq_client'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	nom                  varchar(30)  NOT NULL  ,
	prenom               varchar(30)  NOT NULL  ,
	date_naissance       date  NOT NULL  ,
	email                varchar(30)  NOT NULL  ,
	mdp                  varchar(30)  NOT NULL  ,
	status               integer    ,
	contact              varchar    ,
	CONSTRAINT client_pkey PRIMARY KEY ( id_client )
 );

CREATE  TABLE "public".commission ( 
	id_commission        serial DEFAULT nextval('commission_id_commission_seq'::regclass) NOT NULL  ,
	commission           double precision    ,
	date_changement      date    ,
	CONSTRAINT pk_commission PRIMARY KEY ( id_commission )
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

CREATE  TABLE "public".marque ( 
	id_marque            varchar(7) DEFAULT ('MRQ'::text || lpad((nextval('seq_marque'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT marque_pkey PRIMARY KEY ( id_marque )
 );

CREATE  TABLE "public".modele ( 
	id_modele            varchar(7) DEFAULT ('MDL'::text || lpad((nextval('seq_modele'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	intitule             varchar(30)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT modele_pkey PRIMARY KEY ( id_modele )
 );

CREATE  TABLE "public".voiture ( 
	id_voiture           varchar(7) DEFAULT ('CAR'::text || lpad((nextval('seq_voiture'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_marque            varchar(7)    ,
	id_categorie         varchar(7)    ,
	id_modele            varchar(7)    ,
	id_energie           varchar(7)    ,
	id_couleur           varchar(7)    ,
	anne_sortie          varchar(7)    ,
	immatriculation      varchar(7)    ,
	autonomie            double precision    ,
	CONSTRAINT voiture_pkey PRIMARY KEY ( id_voiture ),
	CONSTRAINT fk_voiture_marque FOREIGN KEY ( id_marque ) REFERENCES "public".marque( id_marque )   ,
	CONSTRAINT fk_voiture_categorie FOREIGN KEY ( id_categorie ) REFERENCES "public".categorie( id_categorie )   ,
	CONSTRAINT fk_voiture_modele FOREIGN KEY ( id_modele ) REFERENCES "public".modele( id_modele )   ,
	CONSTRAINT fk_voiture_energie FOREIGN KEY ( id_energie ) REFERENCES "public".energie( id_energie )   ,
	CONSTRAINT fk_voiture_couleur FOREIGN KEY ( id_couleur ) REFERENCES "public".couleur( id_couleur )   
 );

CREATE  TABLE "public".voiture_photo ( 
	id_voiture           varchar(7)    ,
	photo                text    ,
	CONSTRAINT voiture_photo_id_voiture_fkey FOREIGN KEY ( id_voiture ) REFERENCES "public".voiture( id_voiture )   
 );

CREATE  TABLE "public".annonce ( 
	id_annonce           varchar(7) DEFAULT ('ANO'::text || lpad((nextval('seq_annonce'::regclass))::text, 4, '0'::text)) NOT NULL  ,
	id_voiture           varchar(7)    ,
	description          varchar(100)    ,
	"date"               timestamp DEFAULT CURRENT_TIMESTAMP   ,
	prix                 double precision    ,
	id_client            varchar(7)    ,
	status               integer    ,
	commission           double precision DEFAULT 0   ,
	CONSTRAINT annonce_pkey PRIMARY KEY ( id_annonce ),
	CONSTRAINT fk_annonce_voiture FOREIGN KEY ( id_voiture ) REFERENCES "public".voiture( id_voiture )   ,
	CONSTRAINT fk_annonce_client FOREIGN KEY ( id_client ) REFERENCES "public".client( id_client )   
 );

CREATE OR REPLACE VIEW "public".v_annonce_client AS SELECT a.id_annonce,     a.id_voiture,     a.description,     a.date,     a.prix,     a.id_client,     a.status AS status_annonce,     c.nom,     c.prenom,     c.date_naissance,     c.email,     c.mdp,     c.contact,     c.status AS status_client    FROM (annonce a      JOIN client c ON (((c.id_client)::text = (a.id_client)::text)))
 SELECT a.id_annonce,
    a.id_voiture,
    a.description,
    a.date,
    a.prix,
    a.id_client,
    a.status AS status_annonce,
    c.nom,
    c.prenom,
    c.date_naissance,
    c.email,
    c.mdp,
    c.contact,
    c.status AS status_client
   FROM (annonce a
     JOIN client c ON (((c.id_client)::text = (a.id_client)::text)));

CREATE OR REPLACE VIEW "public".v_detail_annonce AS
 SELECT vdv.id_voiture,
    vdv.id_marque,
    vdv.id_categorie,
    vdv.id_modele,
    vdv.id_energie,
    vdv.id_couleur,
    vdv.anne_sortie,
    vdv.immatriculation,
    vdv.autonomie,
    vdv.marque,
    vdv.categorie,
    vdv.model,
    vdv.energie,
    vdv.couleur,
    vac.id_annonce,
    vac.description,
    vac.date AS date_annonce,
    vac.prix,
    vac.id_client,
    vac.status_annonce,
    vac.nom,
    vac.prenom,
    vac.date_naissance,
    vac.email,
    vac.mdp,
    vac.status_client,
    vac.contact
   FROM (v_detail_voiture vdv
     JOIN v_annonce_client vac ON (((vac.id_voiture)::text = (vdv.id_voiture)::text)));

CREATE OR REPLACE VIEW "public".v_detail_voiture AS SELECT v.id_voiture,     v.id_marque,     v.id_categorie,     v.id_modele,     v.id_energie,     v.id_couleur,     v.anne_sortie,     v.immatriculation,     v.autonomie,     m.intitule AS marque,     c.intitule AS categorie,     mo.intitule AS model,     e.intitule AS energie,     co.intitule AS couleur    FROM (((((voiture v      JOIN marque m ON (((m.id_marque)::text = (v.id_marque)::text)))      JOIN categorie c ON (((c.id_categorie)::text = (v.id_categorie)::text)))      JOIN modele mo ON (((mo.id_modele)::text = (v.id_modele)::text)))      JOIN energie e ON (((e.id_energie)::text = (v.id_energie)::text)))      JOIN couleur co ON (((co.id_couleur)::text = (v.id_couleur)::text)))
 SELECT v.id_voiture,
    v.id_marque,
    v.id_categorie,
    v.id_modele,
    v.id_energie,
    v.id_couleur,
    v.anne_sortie,
    v.immatriculation,
    v.autonomie,
    m.intitule AS marque,
    c.intitule AS categorie,
    mo.intitule AS model,
    e.intitule AS energie,
    co.intitule AS couleur
   FROM (((((voiture v
     JOIN marque m ON (((m.id_marque)::text = (v.id_marque)::text)))
     JOIN categorie c ON (((c.id_categorie)::text = (v.id_categorie)::text)))
     JOIN modele mo ON (((mo.id_modele)::text = (v.id_modele)::text)))
     JOIN energie e ON (((e.id_energie)::text = (v.id_energie)::text)))
     JOIN couleur co ON (((co.id_couleur)::text = (v.id_couleur)::text)));

INSERT INTO "public".categorie( id_categorie, intitule, etat ) VALUES ( 'CTG0001', 'Plaisir', 10);
INSERT INTO "public".categorie( id_categorie, intitule, etat ) VALUES ( 'CTG0002', 'bus', 1);
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0001', '{\r\n    "nom" : "Bleu"\r\n}', 10);
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0002', '"bleu"', 10);
INSERT INTO "public".couleur( id_couleur, intitule, etat ) VALUES ( 'CLR0003', 'Bleu', 1);