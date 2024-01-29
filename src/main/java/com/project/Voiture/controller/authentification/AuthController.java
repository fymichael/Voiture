package com.project.Voiture.controller.authentification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins="*", allowedHeaders="*")
public class AuthController {

    @PostMapping("/login")
    public MyJSON login(@RequestBody VProfil client, HttpServletRequest request, HttpServletResponse response) throws Exception {
             MyJSON json = new MyJSON();
        try {
           new JwtUtils().createTokens(request, response, client.getUsername(), client.getMdp());
           json.setData("Authentification reussi");
        } catch(Exception e) {
              e.printStackTrace();
              json.setError(e.getMessage());
        }
        
        return json;
    }
}
