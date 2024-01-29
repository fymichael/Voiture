package com.project.Voiture.model.frontOffice.messagerie.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.mongodb.client.model.UpdateOptions;
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

    public Object json;

    //Toutes les conversations
    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    //Conversations d'un user
    public List<Discussion> getProfilDiscussions(String idProfil) {
            Query query = new Query(Criteria.where("membres.idProfil").in(idProfil));
            return mongoTemplate.find(query, Discussion.class);
    }

    //Conversation d'un user et autre
    public Discussion getDiscussionWithOtherProfile(String idProfileConnected, String idProfilAutre) {
        Query query = new Query(Criteria.where("membres.idProfil").all(List.of(idProfileConnected, idProfilAutre)));
        
        return mongoTemplate.findOne(query, Discussion.class);
    }

    //Discussion grace a l'id
    public Optional<Discussion> getDiscussionById(String idDiscussion) {
        return discussionRepository.findById(idDiscussion);
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
    public List<Discussion> nombreDiscussionsAvecMessagesNonLus(String idProfil) {
        // Utilisation des opérations d'agrégation pour compter les discussions avec des messages non lus
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("messages"),
            Aggregation.match(Criteria.where("messages.status").is(1).and("messages.emetteur").ne(idProfil)),
            Aggregation.group("_id")
                    .first("membres").as("membres")
                    .addToSet("messages").as("messages")
                    .first("dateCreation").as("dateCreation")
                    .first("status").as("status")
        );

        return mongoTemplate.aggregate(aggregation, "discussion", Discussion.class).getMappedResults();
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
    public void marquerTousLesMessagesCommeLus(String discussionId, String emitterId) {
        Query query = new Query(Criteria.where("_id").is(discussionId));
        Update update = new Update().set("messages.$[el].status", 3);

        update.filterArray(Criteria.where("el.emetteur").ne(emitterId).and("el.status").is(1));

        mongoTemplate.updateMulti(query, update, "discussion");
    }

    //Creer une nouvelle discussion avec un utilisateur
    public Discussion créerNouvelleDiscussion(Profil profilConnected, Profil profil2, Message message) {
       if(message == null) {
            return null;
       }

        // Créer une nouvelle discussion
        Discussion newDiscussion = new Discussion();
        List<Profil> listMembre = new ArrayList<>();
        List<Message> listMessage = new ArrayList<>();
        listMembre.add(profilConnected);
        listMembre.add(profil2);
        listMessage.add(message);
        newDiscussion.setMembres(listMembre);
        newDiscussion.setMessages(listMessage);
        newDiscussion.setDateCreation(LocalDateTime.now().toString());
        newDiscussion.setStatus(1);

        // Enregistrer la nouvelle conversation
        Discussion discussionSaved = discussionRepository.save(newDiscussion);

        return discussionSaved;
    }
}
