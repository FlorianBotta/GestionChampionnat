package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Championship;

import java.util.List;

public interface ChampionshipService {
    Championship ajouterChampionship(Championship championship);
    Championship recupererChampionship(Long id);
    List<Championship> recupererChampionships();
    Championship modifierChampionship(Championship championship);
    void supprimerChampionship(Long id);
}
