package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.ModeTransmission;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class ModeTransmissionController {

    @GetMapping("/modeTransmissions")
    public ModeTransmission[] getListe()throws Exception{
        ModeTransmission c = new ModeTransmission();
        ModeTransmission[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/modeTransmission")
    public void form(@RequestBody ModeTransmission ModeTransmission)throws Exception{
       ModeTransmission.insert(null);
    }

    @PutMapping("/modeTransmission")
    public void update(@RequestBody ModeTransmission ModeTransmission)throws Exception{
       ModeTransmission.update(null);
    }

    @PutMapping("/modeTransmission")
    public void delete(@RequestBody ModeTransmission ModeTransmission)throws Exception{
       ModeTransmission.delete(null);
    }
}
