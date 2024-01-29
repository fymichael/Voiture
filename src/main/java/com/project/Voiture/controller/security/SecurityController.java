package com.project.Voiture.controller.security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.Voiture.securite.filter.JwtUtils;


@RestController
@RequestMapping("api/voiture")
public class SecurityController {

   @GetMapping(path = "/refreshToken")
   public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken =request.getHeader("Authorization");
        if(authToken != null && authToken.startsWith("Bearer ")) {
            try {
              JwtUtils.createRefreshTokens(request, response, authToken);
            } catch(Exception e) {
                throw e;
            }
      } else {
            throw new RuntimeException("Refresh token required");
      }
   }
}
