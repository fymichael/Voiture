CREATE OR REPLACE VIEW v_commission AS
    SELECT c.id_commission, c.valeur , c.date_changement,
        a.id_annonce, a.id_voiture, a."date" AS date_annonce, ABS(c.date_changement - a."date")
        FROM Annonce a JOIN LATERAL ( SELECT c.id_commission, c.valeur, c.date_changement
            FROM Commission c ORDER BY ABS(c.date_changement - a."date"), c.date_changement LIMIT 1
        ) c ON true;

CREATE OR REPLACE VIEW v_marque_plus_vendue AS
    SELECT m.id_marque, m.intitule, COUNT(v.id_voiture) AS nombre_ventes
    FROM voiture v
    JOIN marque m ON v.id_marque = m.id_marque
    JOIN annonce a ON v.id_voiture = a.id_voiture
    JOIN vente ve ON a.id_annonce = ve.id_annonce
    GROUP BY m.id_marque, m.intitule
    ORDER BY COUNT(v.id_voiture) DESC;

CREATE OR REPLACE VIEW v_nombre_element AS
    SELECT
        (SELECT COUNT(*) FROM categorie) AS nb_categorie,
        (SELECT COUNT(*) FROM couleur) AS nb_couleur,
        (SELECT COUNT(*) FROM energie) AS nb_energie,
        (SELECT COUNT(*) FROM marque) AS nb_marque,
        (SELECT COUNT(*) FROM mode_transmission) AS nb_mode_transmission,
        (SELECT COUNT(*) FROM specification) AS nb_specification;


