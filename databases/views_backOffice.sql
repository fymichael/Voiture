CREATE OR REPLACE VIEW v_commission AS
    SELECT c.id_commission, c.valeur , c.date_changement,
        a.id_annonce, a.id_voiture, a."date" AS date_annonce, ABS(c.date_changement - a."date")
        FROM Annonce a JOIN LATERAL ( SELECT c.id_commission, c.valeur, c.date_changement
            FROM Commission c ORDER BY ABS(c.date_changement - a."date"), c.date_changement LIMIT 1
        ) c ON true;
