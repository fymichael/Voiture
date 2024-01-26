package com.project.Voiture.securite.service;

import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.repository.ProfilRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfilDetailsService implements UserDetailsService {

    private final ProfilRepository profilRepository;

    public ProfilDetailsService(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VProfil profil = profilRepository.findByUsername(username, null);
        List<String> roles = new ArrayList<>();
        roles.add(profil.getRole());
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(profil.getEmail())
                        .password(profil.getMdp())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}