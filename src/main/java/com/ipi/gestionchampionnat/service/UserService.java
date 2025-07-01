package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.User;

import java.util.List;

public interface UserService {
    User ajouterUser(User user);
    User recupererUser(Long id);
    List<User> recupererUsers();
    User modifierUser(User user);
    void supprimerUser(Long id);
    User recupererUserByEmail(String email);
    User connecterUser(String email, String password);
}
