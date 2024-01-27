package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.project.Voiture.model.backOffice.caracteristique.Energie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class EnergieController {

   @GetMapping("/energies")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Energie[] getListe()throws Exception{
      Energie c = new Energie();
      Energie[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/energie/{id}")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Energie getById(@PathVariable String id)throws Exception{
        Energie c = new Energie();
        c.setIdEnergie(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/energie")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Energie form(@RequestBody Energie energie)throws Exception{
      return energie.insert(null);
    }

    @PutMapping("/energie")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void update(@RequestBody Energie energie)throws Exception{
       energie.update(null);
    }

    @DeleteMapping("/energie/{id}")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void delete(@PathVariable String id)throws Exception{
        Energie energie=new Energie();
        energie.setIdEnergie(id);
        System.out.println(energie.getIdEnergie());
        energie.delete(null);
    }

}