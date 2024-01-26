package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Voiture.model.backOffice.caracteristique.ModeTransmission;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("api/voiture")
@PostAuthorize("hasAuthority('ROLE_Administrateur')")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ModeTransmissionController {

   @GetMapping("/modeTransmissions")
   public ModeTransmission[] getListe()throws Exception{
      ModeTransmission c = new ModeTransmission();
      ModeTransmission[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/modeTransmission/{id}")
   public ModeTransmission getById(@PathVariable String id)throws Exception{
        ModeTransmission c = new ModeTransmission();
        c.setIdModeTransmission(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/modeTransmission-form")
    public ModeTransmission form(@RequestBody ModeTransmission modeTransmission)throws Exception{
      return modeTransmission.insert(null);
    }

    @PutMapping("/modeTransmission-update")
    public void update(@RequestBody ModeTransmission modeTransmission)throws Exception{
       modeTransmission.update(null);
    }

    @DeleteMapping("/modeTransmission-delete/{id}")
    public void delete(@PathVariable String id)throws Exception{
        ModeTransmission modeTransmission=new ModeTransmission();
        modeTransmission.setIdModeTransmission(id);
        System.out.println(modeTransmission.getIdModeTransmission());
        modeTransmission.delete(null);
    }

}