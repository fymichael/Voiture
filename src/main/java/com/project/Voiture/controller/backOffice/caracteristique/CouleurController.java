package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.project.Voiture.model.backOffice.caracteristique.Couleur;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class CouleurController {

   @GetMapping("/couleurs")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Couleur[] getListe()throws Exception{
      Couleur c = new Couleur();
      Couleur[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/couleur/{id}")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Couleur getById(@PathVariable String id)throws Exception{
        Couleur c = new Couleur();
        c.setIdCouleur(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/couleur")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Couleur form(@RequestBody Couleur couleur)throws Exception{
      return couleur.insert(null);
    }

    @PutMapping("/couleur")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void update(@RequestBody Couleur couleur)throws Exception{
       couleur.update(null);
    }

    @DeleteMapping("/couleur/{id}")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void delete(@PathVariable String id)throws Exception{
        Couleur couleur=new Couleur();
        couleur.setIdCouleur(id);
        System.out.println(couleur.getIdCouleur());
        couleur.delete(null);
    }

}

