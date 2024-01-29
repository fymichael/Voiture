package com.project.Voiture.controller.frontOffice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;
import com.project.Voiture.model.frontOffice.historiquesFavoris.model.Historique;
import com.project.Voiture.model.frontOffice.historiquesFavoris.service.HistoriqueService;
import com.project.Voiture.model.frontOffice.listeAnnonce.views.VAnnonce;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
public class HistoriqueController {
        @Autowired
    private HistoriqueService historiqueService;

   @GetMapping(path = "/historique/save")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON saveHistorique(@RequestParam("type") String type, @RequestParam("idAnnonce") String idAnnonce, HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            VAnnonce annonce = VAnnonce.findOneById(idAnnonce, null);
            if(!type.equals("annonce") && !type.equals("favoris")) {
                throw new Exception("Impossible d'etablir l'operation : le type n'existe pas");
            }
            if(annonce.getIdAnnonce() == null) {
                throw new Exception("Impossible d'etablir l'operation : l'annonce n'existe pas");
            }
            Historique historique = historiqueService.isAnnonceInHistorique(profilConnected.getIdProfil(), idAnnonce, type);
            if(historique != null) {
                throw new Exception("Impossible d'etablir l'operation : l'historique existe deja");
            }
            historiqueService.saveHistorique(profilConnected.getIdProfil(), idAnnonce, type);
            json.setData("Historique a bien ete enregistrer");
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/historique/annonces")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON getAnnonceHistorique(HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            
            List<Historique> listHistoriqueAnnonce = historiqueService.getHistoriqueAnnonceProfil(profilConnected.getIdProfil());
            json.setData(listHistoriqueAnnonce);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/historique/favoris")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON getFavorisHistorique(HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            
            List<Historique> listHistoriqueAnnonce = historiqueService.getFavorisProfil(profilConnected.getIdProfil());
            json.setData(listHistoriqueAnnonce);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
}
