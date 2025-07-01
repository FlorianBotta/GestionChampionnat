package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Stadium;

import java.util.List;

public interface StadiumService {
    Stadium ajouterStadium(Stadium stadium);
    Stadium recupererStadium(Long id);
    List<Stadium> recupererStadiums();
    Stadium modifierStadium(Stadium stadium);
    void supprimerStadium(Long id);
    Stadium recupererStadiumByName(String name);
}
