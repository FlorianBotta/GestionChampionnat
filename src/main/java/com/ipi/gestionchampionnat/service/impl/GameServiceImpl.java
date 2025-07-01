package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.GameDao;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameDao gameDao;

    @Override
    public Game ajouterGame(Game game) {
        return gameDao.save(game);
    }

    @Override
    public Game recupererGame(Long id) {
        return gameDao.findById(id).orElse(null);
    }

    @Override
    public List<Game> recupererGames() {
        return gameDao.findAll();
    }

    @Override
    public Game modifierGame(Game game) {
        return gameDao.save(game);
    }

    @Override
    public void supprimerGame(Long id) {
        gameDao.deleteById(id);
    }

    @Override
    public List<Game> recupererGamesByDay(Day day) {
        return gameDao.findByDay(day);
    }

    @Override
    public List<Game> recupererGamesByTeam(Integer teamId) {
        return gameDao.findByIdTeam1OrIdTeam2(teamId, teamId);
    }
}