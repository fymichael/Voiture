package com.project.Voiture.model.frontOffice.messagerie.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.project.Voiture.model.frontOffice.messagerie.model.Discussion;
import com.project.Voiture.model.frontOffice.messagerie.model.Message;
import com.project.Voiture.model.frontOffice.messagerie.model.NombreDiscussions;
import com.project.Voiture.model.frontOffice.messagerie.model.NombreMessagesNonLus;
import com.project.Voiture.model.frontOffice.messagerie.repository.DiscussionRepository;
import com.project.Voiture.securite.entite.Profil;

@Service
public class MessagerieService {
    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Toutes les conversations
    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    //Conversations d'un user
    public List<Discussion> getProfilDiscussions(String idProfil) {
        return discussionRepository.findByMembres(idProfil);
    }

    //Conversation d'un user et autre
    public List<Discussion> getDiscussion(String idProfileConnected, String idProfilAutre) {
        return discussionRepository.findByMembresIn(Arrays.asList(idProfileConnected, idProfilAutre));
    }

    //Envoyer un message
    public void sendMessage(String idDiscussion, String emetteur, String contenu) {
        // Récupérer la discussion
        Discussion discussion = discussionRepository.findById(idDiscussion)
                .orElseThrow(() -> new RuntimeException("Discussion non trouvée"));

        // Créer un nouveau message
        Message message = new Message(LocalDateTime.now().toString(), emetteur, contenu, 1);

        // Ajouter le message à la liste des messages de la  discussion
        discussion.getMessages().add(message);

        // Enregistrer la mise à jour de la discussion
        discussionRepository.save(discussion);
    }

    //Avoir le nombre de discussion ou il y a un ou des messages non lus
    public long nombreDiscussionsAvecMessagesNonLus(String idProfil) {
        // Utilisation des opérations d'agrégation pour compter les discussions avec des messages non lus
        Aggregation aggregation = newAggregation(
                unwind("$messages"),  // Démêler les messages pour permettre le filtrage
                match(
                        Criteria.where("messages.emetteur").ne(idProfil)
                                .and("messages.statut").is(1) // 1 pour les messages non lus
                ),
                group("_id").count().as("nombreDiscussions")
        );

        AggregationResults<NombreDiscussions> results = mongoTemplate.aggregate(aggregation, "discussion", NombreDiscussions.class);

        List<NombreDiscussions> discussionsAvecMessagesNonLus = results.getMappedResults();

        return discussionsAvecMessagesNonLus.isEmpty() ? 0 : discussionsAvecMessagesNonLus.get(0).getNombreDiscussions();
    }

    //Est ce que la discussion contient des messages non lus
    public boolean conversationContientMessagesNonLus(String conversationId, String idProfil) {
        // Utilisation des opérations d'agrégation pour vérifier si la conversation a des messages non lus
        Aggregation aggregation = newAggregation(
                match(Criteria.where("_id").is(conversationId)),
                unwind("$messages"),
                match(
                        Criteria.where("messages.emetteur").ne(idProfil)
                                .and("messages.statut").is(1) // 1 pour les messages non lus
                ),
                group("_id").count().as("nombreMessagesNonLus")
        );

        AggregationResults<NombreMessagesNonLus> results = mongoTemplate.aggregate(aggregation, "discussion", NombreMessagesNonLus.class);

        List<NombreMessagesNonLus> messagesNonLus = results.getMappedResults();

        return !messagesNonLus.isEmpty() && messagesNonLus.get(0).getNombreMessagesNonLus() > 0;
    }

    //Marquer toutes les messages a lus
    public void marquerTousLesMessagesCommeLus(String conversationId, String idProfil) {
        // Mettre à jour le statut de tous les messages non lus dans la discussion
        Query query = new Query(
            Criteria.where("_id").is(conversationId)
                    .and("messages.emetteur").ne(idProfil)
                    .and("messages.statut").is(1)
        );

        Update update = new Update().set("messages.$.statut", 3); // 3 pour "lu"

        mongoTemplate.updateMulti(query, update, Discussion.class);
    }

    //Verifier l'existence de conversation entre deux utilisateurs
    public String isConversationExist(String profilConnected, String profil2) {
        // Vérifier si une conversation existe déjà entre ces deux utilisateurs
        List<Discussion> existante = discussionRepository.findByMembresIn(List.of(profilConnected, profil2));

        if (existante.size() > 0) {
            return existante.get(0).getId(); // Une conversation existe déjà, renvoyer son ID
        }

        return "null";
    }

    //Creer une nouvelle discussion avec un utilisateur
    public String créerNouvelleDiscussion(Profil profilConnected, Profil profil2, Message message) {
       if(message == null) {
            return "null";
       }

        // Créer une nouvelle discussion
        Discussion newDiscussion = new Discussion();
        newDiscussion.getMembres().add(profilConnected);
        newDiscussion.getMembres().add(profil2);
        newDiscussion.getMessages().add(message);

        // Enregistrer la nouvelle conversation
        Discussion discussionSaved = discussionRepository.save(newDiscussion);

        return discussionSaved.getId();
    }
}