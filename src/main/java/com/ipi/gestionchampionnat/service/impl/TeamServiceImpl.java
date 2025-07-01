package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.TeamDao;
import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public Team ajouterTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team recupererTeam(Long id) {
        return teamDao.findById(id).orElse(null);
    }

    @Override
    public List<Team> recupererTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team modifierTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public void supprimerTeam(Long id) {
        teamDao.deleteById(id);
    }

    @Override
    public List<Team> recupererTeamsByCountry(Country country) {
        return teamDao.findByCountry(country);
    }

    @Override
    public Team recupererTeamByName(String name) {
        return teamDao.findByName(name);
    }
}