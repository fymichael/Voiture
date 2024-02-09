package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Voiture.model.backOffice.caracteristique.Lieux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins="*", allowedHeaders="*")
public class LieuxController {

   @GetMapping("/lieux")
   public Lieux[] getListe()throws Exception{
      Lieux c = new Lieux();
      Lieux[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/lieux/{id}")
   public Lieux getById(@PathVariable String id)throws Exception{
        Lieux c = new Lieux();
        c.setIdLieux(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/lieux")
     public Lieux form(@RequestBody Lieux Lieux)throws Exception{
      return Lieux.insert(null);
    }

    @PutMapping("/lieux")
     public void update(@RequestBody Lieux Lieux)throws Exception{
       Lieux.update(null);
    }

    @DeleteMapping("/lieux/{id}")
     public void delete(@PathVariable String id)throws Exception{
        Lieux Lieux=new Lieux();
        Lieux.setIdLieux(id);
        System.out.println(Lieux.getIdLieux());
        Lieux.delete(null);
    }

}