package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.TeamChampionshipDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;
import com.ipi.gestionchampionnat.service.TeamChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamChampionshipServiceImpl implements TeamChampionshipService {
    @Autowired
    private TeamChampionshipDao teamChampionshipDao;

    @Override
    public TeamChampionship ajouterTeamChampionship(TeamChampionship teamChampionship) {
        return teamChampionshipDao.save(teamChampionship);
    }

    @Override
    public TeamChampionship recupererTeamChampionship(Long id) {
        return teamChampionshipDao.findById(id).orElse(null);
    }

    @Override
    public List<TeamChampionship> recupererTeamChampionships() {
        return teamChampionshipDao.findAll();
    }

    @Override
    public List<TeamChampionship> recupererTeamsByChampionship(Championship championship) {
        return teamChampionshipDao.findByChampionship(championship);
    }

    @Override
    public List<TeamChampionship> recupererChampionshipsByTeam(Team team) {
        return teamChampionshipDao.findByTeam(team);
    }

    @Override
    public void supprimerTeamChampionship(Long id) {
        teamChampionshipDao.deleteById(id);
    }
}