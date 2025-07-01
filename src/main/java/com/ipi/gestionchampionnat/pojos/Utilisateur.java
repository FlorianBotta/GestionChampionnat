package com.ipi.gestionchampionnat.pojos;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Utilisateur implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Est-ce que tu peux m'aider à implémenter cette méthode ?
        // Mon objectif est de renvoyer un User détails basé sur le nom d'utilisateur à partir de mon username.
        return null;
    }
}
