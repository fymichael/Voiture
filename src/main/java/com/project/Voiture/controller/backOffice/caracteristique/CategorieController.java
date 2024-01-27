package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Categorie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class CategorieController {

   @GetMapping("/categories")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Categorie[] getListe()throws Exception{
      try {
         Categorie c = new Categorie();
         Categorie[] liste = c.getAll(null);
         return liste;
      } catch (Exception e) {
         e.printStackTrace();  
         throw e;
      }
   }

   @GetMapping("/categorie/{id}")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Categorie getById(@PathVariable String id)throws Exception{
        Categorie c = new Categorie();
        c.setIdCategorie(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/categorie")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Categorie form(@RequestBody Categorie categorie)throws Exception{
      return categorie.insert(null);
    }

    @PutMapping("/categorie")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void update(@RequestBody Categorie categorie)throws Exception{
       categorie.update(null);
    }

    @DeleteMapping("/categorie/{id}")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void delete(@PathVariable String id)throws Exception{
        Categorie categorie=new Categorie();
        categorie.setIdCategorie(id);
        System.out.println(categorie.getIdCategorie());
        categorie.delete(null);
    }

}