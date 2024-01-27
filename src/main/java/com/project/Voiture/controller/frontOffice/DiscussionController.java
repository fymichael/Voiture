package com.project.Voiture.controller.frontOffice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.*;

import com.project.Voiture.model.backOffice.caracteristique.*;
import com.project.Voiture.model.backOffice.statistique.*;
import com.project.Voiture.model.frontOffice.listeAnnonce.views.*;
import com.project.Voiture.model.frontOffice.messagerie.service.MessagerieService;
import com.project.Voiture.model.frontOffice.listeAnnonce.displays.*;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
public class DiscussionController {
    @Autowired
    private MessagerieService messagerieService;

   @GetMapping(path = "/allDiscussions")
   public MyJSON getAllDiscussions() {
      MyJSON json = new MyJSON();

      try {
        json.setData(messagerieService.getAllDiscussions());
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   /*@PostMapping("/envoyer/{conversationId}")
   public void envoyerMessage(@PathVariable String conversationId, @RequestBody Message message) {
       messagerieService.ajouterMessage(conversationId, message);
   }

   @GetMapping("/conversation/{conversationId}")
   public List<Message> getMessagesConversation(@PathVariable String conversationId) {
       return messagerieService.getMessagesConversation(conversationId);
   }*/
}
