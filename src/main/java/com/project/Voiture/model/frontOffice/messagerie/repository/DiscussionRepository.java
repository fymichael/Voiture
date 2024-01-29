package com.project.Voiture.model.frontOffice.messagerie.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.Voiture.model.frontOffice.messagerie.model.Discussion;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    //Recherche toutes les discussions d'un utilisateur
    List<Discussion> findByMembres(String idMembre);

    //REcherche la discussion d'un utilsateur et autre
    List<Discussion> findByMembresIn(List<String> membres);
}
