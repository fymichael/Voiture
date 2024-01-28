package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.Voiture.model.backOffice.statistique.Commission;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class CommissionController {

   @GetMapping("/commissions")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Commission[] getListe()throws Exception{
      Commission c = new Commission();
      System.out.println("aaa");
      Commission[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/commission/{id}")
   @PostAuthorize("hasAuthority('ROLE_Administrateur')")
   public Commission getById(@PathVariable String id)throws Exception{
        Commission c = new Commission();
        c=c.getCommission(id, null);
        return c;
    }

    @PostMapping("/commission")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Commission form(@RequestBody Commission commission)throws Exception{
      return commission.insert(null);
    }

    @PutMapping("/commission")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void update(@RequestBody Commission commission)throws Exception{
       commission.update(null);
    }

    @DeleteMapping("/commission/{id}")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void delete(@PathVariable String id)throws Exception{
        Commission commission=new Commission();
        commission.setIdCommission(id);
        System.out.println(commission.getIdCommission());
        commission.delete(null);
    }   
}
