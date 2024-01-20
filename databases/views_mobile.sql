
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


CREATE OR REPLACE VIEW "public".v_detail_annonce AS SELECT vdv.id_voiture,     vdv.id_marque,     vdv.id_categorie,     vdv.modele,     vdv.id_energie,     vdv.id_couleur,     vdv.anne_sortie,     vdv.immatriculation,     vdv.autonomie,     vdv.marque,     vdv.categorie,     vdv.model,     vdv.energie,     vdv.couleur,     vac.id_annonce,     vac.description,     vac.date AS date_annonce,     vac.prix,     vac.id_client,     vac.status_annonce,     vac.nom,     vac.prenom,     vac.date_naissance,     vac.email,     vac.mdp,     vac.status_client,     vac.contact    FROM (v_detail_voiture vdv      JOIN v_annonce_client vac ON (((vac.id_voiture)::text = (vdv.id_voiture)::text)))
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
