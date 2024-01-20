package com.project.Voiture.controller.mobile.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.auth.Client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

    @PostMapping("/client")
    public void form(@RequestBody Client newClient) throws Exception {
        newClient.insert(null);
    }

}
