package com.project.Voiture.model.frontOffice.historiquesFavoris.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.project.Voiture.model.frontOffice.historiquesFavoris.model.Historique;
import com.project.Voiture.model.frontOffice.historiquesFavoris.repository.HistoriqueRepository;

@Service
public class HistoriqueService {
    @Autowired
    private HistoriqueRepository historiqueRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Enregistrer une historique
    public void saveHistorique(String idProfil, String idAnnonce, String type) {
        Historique historique = new Historique();
        historique.setIdProfil(idProfil);
        historique.setIdAnnonce(idAnnonce);
        historique.setType(type);
        historique.setDateCreation(LocalDateTime.now().toString());
        historiqueRepository.save(historique);
    }

    //Enregistrer une historique
    public Historique isAnnonceInHistorique(String idProfil, String idAnnonce, String type) {
        Query query = new Query(Criteria.where("idProfil").is(idProfil).and("idAnnonce").is(idAnnonce).and("type").is(type));
        List<Historique> listHistorique = mongoTemplate.find(query, Historique.class);
        if(listHistorique.size() <= 0) {
            return null;
        } 
        return listHistorique.get(0);
    }

    //Liste des historiques annonces
    public List<Historique> getHistoriqueAnnonceProfil(String idProfil) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("idProfil").is(idProfil).and("type").is("annonce")
        );

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Historique.class);
    }

    //Liste des favoris
    public List<Historique> getFavorisProfil(String idProfil) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where("idProfil").is(idProfil).and("type").is("favoris")
        );

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Historique.class);
    }
}
