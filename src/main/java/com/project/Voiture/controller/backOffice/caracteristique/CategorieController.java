package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Categorie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class CategorieController {

   @GetMapping("/categories")
   public Categorie[] getListe()throws Exception{
      Categorie c = new Categorie();
      Categorie[] liste=c.getAll(null);
      return liste;
   }

    @PostMapping("/categorie")
    public void form(@RequestBody Categorie c)throws Exception{
       c.insert(null);
    }

    @PutMapping("/categorie")
    public void update(@RequestBody Categorie categorie)throws Exception{
       categorie.update(null);
    }

    @DeleteMapping("/categorie")
    public void delete(@RequestBody Categorie categorie)throws Exception{
       categorie.delete(null);
    }
}
