package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.objet.Voiture;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VoitureController {

    @PostMapping("/voiture")
    public void form(@RequestBody Voiture newVoiture) throws Exception {
        newVoiture.insert(null);
    }

}
