CREATE OR REPLACE VIEW v_commission AS
    SELECT c.id_commission, c.valeur , c.date_changement,
        a.id_annonce, a.id_voiture, a."date" AS date_annonce, ABS(c.date_changement - a."date")
        FROM Annonce a JOIN LATERAL ( SELECT c.id_commission, c.valeur, c.date_changement
            FROM Commission c ORDER BY ABS(c.date_changement - a."date"), c.date_changement LIMIT 1
        ) c ON true;



CREATE OR REPLACE VIEW v_nombre_element AS
    SELECT
        (SELECT COUNT(*) FROM categorie) AS nb_categorie,
        (SELECT COUNT(*) FROM couleur) AS nb_couleur,
        (SELECT COUNT(*) FROM energie) AS nb_energie,
        (SELECT COUNT(*) FROM marque) AS nb_marque,
        (SELECT COUNT(*) FROM mode_transmission) AS nb_mode_transmission,
        (SELECT COUNT(*) FROM specification) AS nb_specification;



CREATE OR REPLACE VIEW v_marque AS 
SELECT m.id_marque,intitule,COUNT(m.id_marque) nombre_ventes
    FROM vente v
    JOIN annonce a ON v.id_annonce=a.id_annonce
    JOIN voiture c ON a.id_voiture=c.id_voiture
    JOIN marque m ON m.id_marque=c.id_marque
GROUP BY m.id_marque,intitule; 

CREATE OR REPLACE VIEW v_marque1 AS
SELECT 
    id_marque,intitule,0 nombre_ventes
    from marque 
    UNION ALL
    SELECT*from v_marque

CREATE OR REPLACE VIEW v_marque_plus_vendue AS
    SELECT id_marque,intitule, MAX(nombre_ventes) nombre_ventes
        FROM v_marque1
    GROUP BY id_marque,intitule ORDER BY nombre_ventes DESC;






