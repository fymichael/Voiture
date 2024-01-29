package com.project.Voiture.model.frontOffice.historiquesFavoris.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.Voiture.model.frontOffice.historiquesFavoris.model.Historique;

public interface HistoriqueRepository extends MongoRepository<Historique, String> {
    List<Historique> findByIdProfil(String idProfil);
}
