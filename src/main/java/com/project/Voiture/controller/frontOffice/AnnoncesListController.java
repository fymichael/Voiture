package com.project.Voiture.controller.frontOffice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.Voiture.model.backOffice.caracteristique.*;
import com.project.Voiture.model.frontOffice.listeAnnonce.views.*;
import com.project.Voiture.model.frontOffice.listeAnnonce.displays.*;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
public class AnnoncesListController {

   @GetMapping(path = "/accueil")
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
   public MyJSON getAnnonces(@RequestParam("param") String filter, @RequestParam("value") String value) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByFeatures(filter, value, null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @GetMapping(path = "/annonce/{id}")
   public MyJSON getFicheAnnonce(@PathVariable("id") String id) {
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

   @PostMapping(path = "/annonces/filter/prix")
   public MyJSON getAnnoncesByPrix(@RequestParam("prixMin") String prixMin, @RequestParam("prixMax") String prixMax) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByPrix(prixMin, prixMax, null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @PostMapping(path = "/annonces/filter/date")
   public MyJSON getAnnoncesByDate(@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByDateAnnonce(dateMin, dateMax, null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @PostMapping(path = "/annonces/filter/keyWord")
   public MyJSON getAnnoncesByKeyWord(@RequestParam("keyWord") String keyWord) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByKeyWord(keyWord, null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }

   @PostMapping(path = "/annonces/filter/multiCritere")
   public MyJSON getAnnoncesByMultiCritere(HttpServletRequest request, HttpServletResponse response) {
      AnnoncePage annoncePage = new AnnoncePage();
      MyJSON json = new MyJSON();

      try {
         List<VAnnonce> allAnnonce = VAnnonce.findByMultiCritere(request, null);
         annoncePage = new AnnoncePage(allAnnonce);
         json.setData(annoncePage);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
}
