package com.project.Voiture.controller.frontOffice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.Voiture.model.frontOffice.messagerie.displays.DiscussionDisplays;
import com.project.Voiture.model.frontOffice.messagerie.model.Discussion;
import com.project.Voiture.model.frontOffice.messagerie.model.Message;
import com.project.Voiture.model.frontOffice.messagerie.service.MessagerieService;
import com.project.Voiture.securite.entite.Profil;
import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;
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

   @GetMapping(path = "/discussion/{idProfileToDiscuss}")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON getDiscussions(@PathVariable("idProfileToDiscuss") String idProfileToDiscuss, HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            List<Discussion> liste = messagerieService.getProfilDiscussions(profilConnected.getIdProfil());
            Discussion listeTemp = messagerieService.getDiscussionWithOtherProfile(profilConnected.getIdProfil(), idProfileToDiscuss);

            json.setData(new DiscussionDisplays(liste, listeTemp));
            //json.setData(liste);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @PostMapping(path = "/discussion/sendMessage/{idDiscussion}")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON sendMessage(@PathVariable("idDiscussion") String idDiscussion, HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);

            messagerieService.sendMessage(idDiscussion, profilConnected.getIdProfil(), request.getParameter("content"));

            json.setData(messagerieService.getDiscussionById(idDiscussion));
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/discussion/messagesNoRead")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON messagesNoRead(HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);

            System.out.println("Profil = "+profilConnected.getIdProfil());
            List<Discussion> nbMessageNoRead = messagerieService.nombreDiscussionsAvecMessagesNonLus(profilConnected.getIdProfil()); 

            json.setData(nbMessageNoRead.size());
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   
   @GetMapping(path = "/discussion/readMessage/{idDiscussion}")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON readMessage(@PathVariable("idDiscussion") String idDiscussion, HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);

            messagerieService.marquerTousLesMessagesCommeLus(idDiscussion, profilConnected.getIdProfil());
            
            json.setData(messagerieService.getDiscussionById(idDiscussion));
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
   @PostMapping(path = "/discussion/openNewDiscussion/{idProfil2}")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON openNewMessage(@PathVariable("idProfil2") String idProfil2, HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            Discussion discussion = messagerieService.getDiscussionWithOtherProfile(profilConnected.getIdProfil(), idProfil2);
            if(profilConnected.getIdProfil().equals(idProfil2)) {
               throw new Exception("Vous ne pouvez pas ouvrir une discussion a vous meme");
            }
            if(discussion != null) {
               json.setData(discussion);
            } else {
               Profil profil2 = Profil.findById(idProfil2, null);
               Profil profil1 = Profil.findById(profilConnected.getIdProfil(), null);
               Message message = new Message(LocalDateTime.now().toString(), profilConnected.getIdProfil(), request.getParameter("content"), 1);
               if(message.getContenu() == null) {
                  json.setData("Discussion non enregistrer");
               } else {
                  Discussion newDiscussion = messagerieService.créerNouvelleDiscussion(profil1, profil2, message);  
                  json.setData(newDiscussion);
               }
            }
          
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
}
