package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;

import java.util.List;

public interface GameService {
    Game ajouterGame(Game game);
    Game recupererGame(Long id);
    List<Game> recupererGames();
    Game modifierGame(Game game);
    void supprimerGame(Long id);
    List<Game> recupererGamesByDay(Day day);
    List<Game> recupererGamesByTeam(Team teamId);
}
