package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;

public interface TeamService {
    Team ajouterTeam(Team team);
    Team recupererTeam(Long id);
    List<Team> recupererTeams();
    Team modifierTeam(Team team);
    void supprimerTeam(Long id);
    List<Team> recupererTeamsByCountry(Country country);
    Team recupererTeamByName(String name);
}
