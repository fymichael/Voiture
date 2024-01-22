package com.project.Voiture.controller.frontOffice.listeAnnonces;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.security.access.prepost.PostAuthorize;

import java.util.*;

import com.project.Voiture.model.backOffice.caracteristique.*;
import com.project.Voiture.model.backOffice.statistique.*;
import com.project.Voiture.model.frontOffice.listeAnnonce.views.*;
import com.project.Voiture.model.frontOffice.listeAnnonce.displays.*;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
public class AnnoncesListController {

   @GetMapping(path = "/accueil")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON getAccueil() {
      AccueilPage accueilPage = new AccueilPage();
      MyJSON json = new MyJSON();

      try {
         List<Categorie> allCategorie = Categorie.getAllCategorie(null);
         List<Marque> allMarque = Marque.getAllMarque(null);
 
         accueilPage = new AccueilPage(allCategorie, allMarque);
         json.setData(accueilPage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/annonces")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public MyJSON getAnnonces() {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = allAnnonce = VAnnonce.findAll(null);
        
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/annonces/filter")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public MyJSON getAnnonces(@RequestParam("param") String filter, @RequestParam("value") String value) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByFeatures(filter, Integer.valueOf(value), null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/annonce/{id}")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public MyJSON getFicheAnnonce(@PathVariable("id") int id) {
      MyJSON json = new MyJSON();

      try {
         VAnnonce oneAnnonce = VAnnonce.findOneById(id, null);
         json.setData(new FicheAnnoncePage(oneAnnonce));
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
}
