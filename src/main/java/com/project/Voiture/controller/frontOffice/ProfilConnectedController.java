package com.project.Voiture.controller.frontOffice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PostAuthorize;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;
import com.project.Voiture.util.*;


@RestController
@RequestMapping("api/voiture")
public class ProfilConnectedController {

   @GetMapping(path = "/myProfil")
   @PostAuthorize("hasAuthority('ROLE_Client')")
   public MyJSON getMyProfil (HttpServletRequest request, HttpServletResponse response) {
      MyJSON json = new MyJSON();

      try {
            String authToken =request.getHeader("Authorization");
            VProfil profilConnected = JwtUtils.getProfil(authToken);
            
            json.setData(profilConnected);
      } catch(Exception e) {
         e.printStackTrace();
         json.setError(e.getMessage());
      }

      return json;
   }
}