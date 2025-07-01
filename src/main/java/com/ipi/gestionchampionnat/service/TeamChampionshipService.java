package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;

import java.util.List;

public interface TeamChampionshipService {
    TeamChampionship ajouterTeamChampionship(TeamChampionship teamChampionship);
    TeamChampionship recupererTeamChampionship(Long id);
    List<TeamChampionship> recupererTeamChampionships();
    List<TeamChampionship> recupererTeamsByChampionship(Championship championship);
    List<TeamChampionship> recupererChampionshipsByTeam(Team team);
    void supprimerTeamChampionship(Long id);
}